package com.erick.challenge.api.domain.model;

import lombok.Builder;

@Builder
public record ApiError(String message, Integer errorCode) {}