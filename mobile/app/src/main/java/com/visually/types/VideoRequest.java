package com.visually.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VideoRequest(@JsonProperty("video_bytes") String videoBytes, @JsonProperty("object_name") String objectName) {}

