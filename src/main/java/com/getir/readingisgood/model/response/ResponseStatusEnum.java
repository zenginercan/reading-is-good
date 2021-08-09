package com.getir.readingisgood.model.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseStatusEnum {

    OK(200, "OK"),
    DEFAULT_FAIL_CODE(400, "DEFAULT_FAIL_CODE"),
    DEFAULT_FORBIDDEN_CODE(403, "DEFAULT_FORBIDDEN_CODE");

    private Integer statusId;
    private String statusName;


}
