package com.tingotango.model;

import lombok.Data;

@Data
public class NodeDECircular {
    private Kid data;
    private NodeDECircular previous;
    private NodeDECircular next;
    public NodeDECircular (Kid data){
        this.data = data;
    }
}
