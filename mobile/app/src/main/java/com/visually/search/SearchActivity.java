package com.visually.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.visually.R;
import com.visually.adapter.ASLListAdapter;
import com.visually.client.SearchASLClient;
import com.visually.environment.Environment;
import com.visually.executors.RetrofitCallExecutor;
import com.visually.types.SearchASLResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchEditText;

    private ImageButton searchButton;

    private RetrofitCallExecutor retrofitCallExecutor;

    private SearchASLClient searchASLClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        retrofitCallExecutor = new RetrofitCallExecutor(Environment.ROOT_PATH);
        searchASLClient = retrofitCallExecutor.getService(SearchASLClient.class);

        // TODO add listener
        searchButton.setOnClickListener(this::getASLMaterials);
    }

    private void getASLMaterials(View view) {
        final String searchQuery = searchEditText.getQuery().toString();

        if(searchQuery.isEmpty()) {
            return;
        }

        Log.i("getASLMaterials", "searchQuery: " + searchQuery);
        doSearchASLMaterials(searchQuery);
    }

    private void doSearchASLMaterials(final String searchQuery) {
        Call<List<SearchASLResponse>> aslMaterialsCall = searchASLClient.searchASLMaterials(searchQuery, 5);
        aslMaterialsCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<SearchASLResponse>> call, Response<List<SearchASLResponse>> response) {
                List<SearchASLResponse> aslMaterials = response.body();

                if (!response.isSuccessful()) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().toString());
                        Log.i("searchASLCall NotSuccess", response.code() + ";message=" + response.message() + ";error=" + response.errorBody().string());
                        Toast.makeText(getApplicationContext(), jObjError.getString("error_message"), Toast.LENGTH_LONG).show();
                        //showAlert(DailyJournalActivity.this, "Error", jObjError.getString("error_message"), R.style.MyAlertDialogStyle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Something went wrong: Json", Toast.LENGTH_SHORT).show();
                        ;
                        e.printStackTrace();
                    }
                    return;
                }

                Log.i("searchASL", "Size: " + aslMaterials.size());

                if (Objects.nonNull(aslMaterials)) {
                    if (!aslMaterials.isEmpty()) {
                        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(new ASLListAdapter(aslMaterials, SearchActivity.this));

                    } else {
                        Toast.makeText(getApplicationContext(), "No ASL materials found.", Toast.LENGTH_SHORT).show();
//                        showAlert(DailyJournalActivity.this, "You have no journals yet.", "You can start sharing your thoughts by adding a journal.",
//                                (dialogInterface, i) -> doNewJournal());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No ASL materials found.", Toast.LENGTH_SHORT).show();
//                    showAlert(DailyJournalActivity.this, "You have no journals yet.", "You can start sharing your thoughts by adding a journal.",
//                            (dialogInterface, i) -> doNewJournal());
                }

            }

            @Override
            public void onFailure(Call<List<SearchASLResponse>> call, Throwable t) {
                Log.i("searchASLCall Error", t.getMessage());
            }
        });
    }
}
