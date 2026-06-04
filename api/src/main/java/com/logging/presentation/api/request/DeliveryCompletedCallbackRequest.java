package com.logging.presentation.api.request;

import lombok.Data;

@Data
public class DeliveryCompletedCallbackRequest {

    private DeliveryStatus status;
    private String comment;

    public enum DeliveryStatus {
        SUCCESS,
        FAILED
    }
}
