package com.tingotango.model;

import com.tingotango.controller.DTO.DataStructureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Game {
    private ListDECircular participants;
    private List<Question> questions;
    private boolean gameState;
    private boolean answerState;
    private Kid awaitingKid;
    private NodeDECircular awaitingNode;
    private DataStructureDTO awaitingQuestion;
}
