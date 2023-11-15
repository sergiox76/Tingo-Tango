package com.tingotango.service;

import com.tingotango.exceptions.KidsException;
import com.tingotango.model.Kid;
import com.tingotango.model.ListaDECircular;
import com.tingotango.model.NodeDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListaDECircularService {
    private ListaDECircular kids;

    public ListaDECircularService (){

        kids = new ListaDECircular();
        kids.addKidToEnd(new Kid("Sergio Nuñez","001"));
        kids.addKidToEnd(new Kid("Valeria Osorio","002"));
        kids.addKidToEnd(new Kid("Jhair Torres","003"));
        kids.addKidToEnd(new Kid("Sebastian Rugeles","004"));
        kids.addKidToEnd(new Kid("John Madrid","005"));
    }
    public List<Kid> getAll() throws KidsException{
        try {
            return kids.getAll();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }//fin obtener todos

    public String addKidToEnd(Kid kid) {
        kids.addKidToEnd(kid);
        return "Adicionado";
    }

    public String addToStart(Kid kid){
        kids.addToStart(kid);
        return "Adicionado";
    }

    public String addInPosition(int pos, Kid kid){
        kids.addInPosition(pos,kid);
        return "Adicionado";
    }
}
