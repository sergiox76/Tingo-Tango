package com.tingotango.controller.DTO;

import com.tingotango.model.Kid;
import com.tingotango.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class DataStructureDTO {
    private Kid kidData;
    private Question questionData;
}
