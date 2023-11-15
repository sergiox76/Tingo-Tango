package com.tingotango.model;

import lombok.Data;

@Data
public class ListaDECircular {
    private NodeDECircular head;
    private NodeDECircular tail;
    private int size;

}
