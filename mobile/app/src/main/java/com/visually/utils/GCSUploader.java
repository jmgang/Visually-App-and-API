package com.visually.utils;

import android.content.Context;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import android.database.Cursor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class GCSUploader {

    public static String uploadObject(Context context, Uri fileUri) throws IOException {
        String projectId = "axiomatic-treat-421318";
        String bucketName = "visually-app";

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        String objectName = "sl-uploads/" + generateRandomFilename();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Storage.BlobWriteOption precondition;
        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition =
                    Storage.BlobWriteOption.generationMatch(
                            storage.get(bucketName, objectName).getGeneration());
        }

        // Path path = Paths.get(filePath);
        InputStream inputStream = context.getContentResolver().openInputStream(fileUri);
        storage.createFrom(blobInfo, inputStream, precondition);

        Log.i("uploadObject",
                "File " + fileUri + " uploaded to bucket " + bucketName + " as " + objectName);

        return objectName;
    }

    private static String generateRandomFilename() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String timestamp = String.valueOf(System.currentTimeMillis());
        return uuid + "_" + timestamp;
    }
}
