package com.arya.browsecityapi.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiError {

    private Integer errorCode;
    private String errorMessage;
    private Date timestamp;
}
