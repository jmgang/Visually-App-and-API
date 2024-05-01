package com.visually.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignLanguageTranslationResponse(
        @JsonProperty("actions") String actions,
        @JsonProperty("sign_language") String signLanguage
        ) { }
