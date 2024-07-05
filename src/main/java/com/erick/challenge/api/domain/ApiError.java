package com.erick.challenge.api.domain;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record ApiError(String message, Integer errorCode) implements Serializable {}