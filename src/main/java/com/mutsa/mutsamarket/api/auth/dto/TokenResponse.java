package com.mutsa.mutsamarket.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(@JsonProperty String accessToken) {
}
