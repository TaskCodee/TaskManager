package com.taskcodee.server.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiSuccess {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ApiSuccess(String message) {
        this.message = message;
    }

}
