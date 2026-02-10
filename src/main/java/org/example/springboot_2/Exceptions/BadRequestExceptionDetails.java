package org.example.springboot_2.Exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String details;
    private String devloperMessage;
    private LocalDateTime timeStand;



}
