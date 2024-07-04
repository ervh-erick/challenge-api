package com.erick.challenge.api.domain;

import lombok.Builder;

@Builder
public record ApiError(String message, Integer errorCode) {}