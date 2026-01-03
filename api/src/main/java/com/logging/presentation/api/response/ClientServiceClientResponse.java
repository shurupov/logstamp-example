package com.logging.presentation.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientServiceClientResponse {
    private Long clientId;
    private String lastName;
    private String firstName;
}
