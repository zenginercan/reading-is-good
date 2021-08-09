package com.getir.readingisgood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse {

    private ResponseStatusEnum status;
    private String message;
    private Object responseBody;

}
