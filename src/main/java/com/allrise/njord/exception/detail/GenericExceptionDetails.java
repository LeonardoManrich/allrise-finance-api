package com.allrise.njord.exception.detail;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenericExceptionDetails {
    private String title;

    private int status;

    private Object details;

    private String developerMessage;

    private LocalDateTime timestap;
}
