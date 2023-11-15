package com.tingotango.controller;

import com.tingotango.controller.DTO.ResponseDTO;
import com.tingotango.exceptions.KidsException;
import com.tingotango.service.ListaDECircularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tingotango")
public class ListaDECircularController {
    @Autowired
    private ListaDECircularService listaDECircularService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listaDECircularService.getAllKids(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors), HttpStatus.OK);
        }//FIN DEL TRY
    }
}
