package com.tingotango.service;


import com.tingotango.controller.DTO.DataStructureDTO;
import com.tingotango.exceptions.KidsException;
import com.tingotango.model.Game;
import com.tingotango.model.Kid;
import com.tingotango.model.NodeDECircular;
import com.tingotango.model.Question;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Data
public class Tingo_TangoService {
    private final QuestionService questionService;
    private final ListaDECircularService listDEService;
    //Tingo Tango object
    private Game game;
    @Autowired
    public Tingo_TangoService(QuestionService questionService,ListaDECircularService listDEService){
        this.listDEService = listDEService;
        this.questionService = questionService;

        this.game = new Game(listDEService.getKids(),
                questionService.getAll(),false,false,
                null,null,null);
    }


    public String addNewQuestion (Question newQuestion){
        questionService.addNewQuestion(newQuestion);
        return "Pregunta adicionada";
    }

    public String deleteQuestionById(String questionId) throws KidsException {
        if(!game.isGameState()){
            try {
                return questionService.deleteQuestionById(questionId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            throw new KidsException("No se puede eliminar una pregunta si el juego esta en curso");
        }
    }

    public String updateQuestionById(String questionId,Question updQuestion)throws KidsException {
        if (!game.isGameState()) {
            try {
                return questionService.updateQuestion(questionId, updQuestion);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new KidsException("No se puede actualizar una pregutna con el juego en curso");
        }
    }

    public List<Question> getQuestions(){
        return questionService.getAll();
    }

    public Question getQuestionById(String questionId) throws KidsException{
        try {
            return questionService.getQuestionById(questionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addKidToEnd (Kid kid){
        return listDEService.addToEnd(kid);
    }

    public String addToStart (Kid kid){
        return listDEService.addToStart(kid);
    }

    public String deleteKidInPos(int pos)throws KidsException{
        if(!game.isGameState()) {
            try {
                return listDEService.deleteInPos(pos);
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        }
        else {
            throw new KidsException("No se puede eliminar un participante " +
                    "mientras el juego esta en curso");
        }
    }

    public List<Kid> getAllKids () throws KidsException{
        try {
            return listDEService.getAll();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public String moveKid (int pos, String kidId) throws KidsException{
        if (game.getAwaitingKid()==null || !kidId.equals(game.getAwaitingKid().getId())) {
            try {
                listDEService.moveKid(pos,kidId);
                return "Niño movido";
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        } else {
            throw new KidsException("No se puede mover puesto que no se ha respondido");
        }
    }

    public DataStructureDTO roleGame() throws KidsException{
        if(game.getAwaitingKid()==null) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(2000);
            int actualKidPosition = randomPosition % listDEService.getKids().getSize();
            int actualQuestionPos = randomPosition % questionService.getAll().size();

            NodeDECircular temp;
            if (game.getAwaitingKid() == null) {
                temp = listDEService.getKids().getHead();
            } else {
                temp = game.getAwaitingNode();
            }
            while (actualKidPosition > 0) {
                temp = temp.getNext();
                actualKidPosition--;
            }
            Question question = questionService.getAll().get(actualQuestionPos);
            Question newQuestion = new Question(question.getQuestion(),question.getOptions(),
                    null,question.getId());

            game.setAnswerState(true);
            game.setAwaitingKid(temp.getData());
            game.setGameState(true);
            game.setAwaitingNode(temp);
            game.setAwaitingQuestion(new DataStructureDTO(temp.getData(),newQuestion) );

            return new DataStructureDTO(temp.getData(), newQuestion);
        }
        else {
            throw new KidsException("No se puede jugar si todavia no se ha respondido");
        }

    }
    public DataStructureDTO getQuestion (){
        return game.getAwaitingQuestion();
    }

    public String answerQuestion(DataStructureDTO response)throws KidsException{
        if(response.getKidData().getId().equals(game.getAwaitingQuestion().getKidData().getId())){

            Question question;
            try {
                question = questionService.getQuestionById(response.getQuestionData().getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(question.getCorrectPos().equals(response.getQuestionData().getCorrectPos())){
                game.setAnswerState(false);
                game.setAwaitingKid(null);
                return "Respuesta correcta, continua"+ game.getAwaitingQuestion().getKidData().getName();
            }
            else {
                //Change parameters
                game.setAwaitingNode(game.getAwaitingNode().getNext());
                game.setAnswerState(false);
                listDEService.getKids().deleteById(game.getAwaitingKid().getId());
                game.setAwaitingKid(null);
                return "Jugador eliminado continua"+game.getAwaitingNode().getData().getName();
            }
        }
        else{
            throw new KidsException("Debe respodner el jugador preguntado");
        }
    }
}

