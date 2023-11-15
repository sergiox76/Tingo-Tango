package com.tingotango.model;

import com.tingotango.exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListaDECircular {
    private NodeDECircular head;
    private int size;

    public List<Kid> getAll() throws KidsException {
        if(this.head==null){
            throw new KidsException("Lista vacia");
        }
        else{
            List<Kid> kids = new ArrayList<>();
            NodeDECircular temp = this.head;
            do{
                kids.add(temp.getData());
                temp = temp.getNext();
            }
            while (temp!=this.head);
            return kids;
        }
    }//fin metodo obtener todos

    public void addKidToEnd(Kid kid){
        NodeDECircular newNode = new NodeDECircular(kid);
        if(this.head == null){
            this.head = newNode;
            this.head.setNext(this.head);
            this.head.setPrevious(this.head);
        } else {
            this.head.getPrevious().setNext(newNode);
            newNode.setPrevious(this.head.getPrevious());
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
        }
        this.size++;
    }// fin metodo añadir al final

    public void addToStart(Kid kid){
        NodeDECircular newNode = new NodeDECircular(kid);
        if (this.head ==null){
            this.head=newNode;
            this.head.setPrevious(this.head);
            this.head.setNext(this.head);
        }
        else{
            NodeDECircular lastNode = this.head.getPrevious();
            this.head.setPrevious(newNode);
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            this.head=newNode;
        }
        this.size++;
    }//fin metodo añadir al inicio

    public void addInPosition (int pos, Kid kid) {
        if (pos == 1) {
            this.addToStart(kid);

        } else if (pos > this.size) {
            this.addKidToEnd(kid);

        } else if (pos <= this.size) {
            NodeDECircular temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDECircular newNode = new NodeDECircular(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }
    }//fin metodo añadir en posicion

    public void deleteByPosition (int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if(pos == 1){
            NodeDECircular lastNode = this.head.getPrevious();
            lastNode.setNext(this.head.getNext());
            this.head.setPrevious(lastNode);
            this.head = this.head.getNext();
        }
        else{
            NodeDECircular temp = this.head;
            int cont = 1;
            while (cont < pos -1){
                temp = temp.getNext();
                cont++;
            }
            temp.getNext().getNext().setPrevious(temp);
            temp.setNext(temp.getNext().getNext());
        }
        this.size--;
        System.out.println("New size value: "+this.size);
    }//fin metodo eliminar en posicion

    public NodeDECircular deleteById(String id) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacia");
        } else {
            NodeDECircular temp = this.head;
            do {
                if (temp.getData().getId().equals(id)) {

                    if (temp == this.head) {
                        this.head = this.head.getNext();
                        this.head.setPrevious(temp.getPrevious());
                    }

                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(temp.getPrevious());
                    }

                    if (temp.getPrevious() != null) {
                        temp.getPrevious().setNext(temp.getNext());
                    }
                    this.size--;
                    return temp;
                }
                temp = temp.getNext();
            } while (temp != this.head);
            throw new KidsException("ID no encontrado");
        }
    }

    public void moveKid(int pos, String kidId, String direction) throws KidsException {
        int actualPos = pos % this.size;
        try {
            NodeDECircular deletedKid = this.deleteById(kidId);
            if (direction.equalsIgnoreCase("izquierda")) {
                for (int i = 0; i < actualPos; i++) {
                    deletedKid = deletedKid.getPrevious();
                }
            } else if (direction.equalsIgnoreCase("derecha")) {
                for (int i = 0; i < actualPos; i++) {
                    deletedKid = deletedKid.getNext();
                }
            } else {
                throw new KidsException("Dirección no válida. Por favor, elige 'izquierda' o 'derecha'.");
            }
            this.addInPosition(actualPos, deletedKid.getData());
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }//fin metodo mover en posicion
}
