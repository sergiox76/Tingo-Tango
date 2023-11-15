package com.tingotango.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question  {
    private String question;
    private String id;
    private List<String> option;
}
