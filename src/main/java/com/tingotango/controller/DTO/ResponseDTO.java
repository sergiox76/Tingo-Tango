package com.tingotango.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseDTO {
    private int code;
    Object data;
    List<String>error;
}
