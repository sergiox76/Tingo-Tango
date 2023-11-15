package com.tingotango.model;

import com.tingotango.exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListaDECircular {
    private NodeDECircular head;
    private NodeDECircular tail;
    private int size;

    public List<Kid> getAllKids() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else {
            List<Kid> kids = new ArrayList<>();
            NodeDECircular current = this.head;

            do {
                kids.add(current.getData());
                current = current.getNext();
            } while (current != null && current != this.head);

            return kids;
        }
    }//fin obtener todos los niños

    public void addKidToEnd(Kid kid) {
        NodeDECircular newNode = new NodeDECircular(kid);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
        } else {
            newNode.setPrevious(this.tail);
            newNode.setNext(this.head);
            this.tail.setNext(newNode);
            this.head.setPrevious(newNode);
            this.tail = newNode;
        }

        this.size++;
    }
}
