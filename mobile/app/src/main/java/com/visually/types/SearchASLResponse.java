package com.visually.types;

import com.fasterxml.jackson.annotation.JsonProperty;


public record SearchASLResponse(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("url") String url,
        @JsonProperty("video_url") String videoUrl
) {}


