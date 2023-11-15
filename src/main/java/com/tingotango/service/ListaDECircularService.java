package com.tingotango.service;

import com.tingotango.model.Kid;
import com.tingotango.model.ListaDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListaDECircularService {
    private ListaDECircular kids;

    public ListaDECircularService (){

        kids = new ListaDECircular();
        kids.addKidToEnd(new Kid("Sergio Nuñez","001",(byte)19));
        kids.addKidToEnd(new Kid("Valeria Osorio","002",(byte)20));
        kids.addKidToEnd(new Kid("Jhair Torres","003",(byte)19));
        kids.addKidToEnd(new Kid("Sebastian Rugeles","004",(byte)19));
        kids.addKidToEnd(new Kid("John Madrid","005",(byte)19));
    }
}
