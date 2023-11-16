package com.tingotango.service;

import com.tingotango.exceptions.KidsException;
import com.tingotango.model.Kid;
import com.tingotango.model.ListDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListaDECircularService {
    private ListDECircular kids;

    public ListaDECircularService (){

        kids = new ListDECircular();
        kids.addToEnd(new Kid("Sergio Nuñez","001"));
        kids.addToEnd(new Kid("Valeria Osorio","002"));
        kids.addToEnd(new Kid("Jhair Torres","003"));
        kids.addToEnd(new Kid("Sebastian Rugeles","004"));
        kids.addToEnd(new Kid("John Madrid","005"));
    }
    public List<Kid> getAll() throws KidsException{
        try {
            return kids.getAll();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }
    public String addToEnd(Kid kid) {
        kids.addToEnd(kid);
        return "Adicionado";
    }
    public String addToStart(Kid kid){
        kids.addToStart(kid);
        return "Adicionado";
    }
    public String deleteInPos(int pos) throws KidsException{
        try {
            kids.deleteByPos(pos);
            return "Eliminado";
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }
    public String insertInPos(int pos, Kid kid){
        kids.insertInPos(pos,kid);
        return "Adicionado";
    }


    public String moveKid(int pos, String kidId) throws KidsException{
        try {
            kids.moveKid(pos,kidId);
            return "Niño movido";
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }
}
