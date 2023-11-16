package com.tingotango.service;

import com.tingotango.exceptions.KidsException;
import com.tingotango.model.ListQuestion;
import com.tingotango.model.Question;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Service
@Data
public class QuestionService {
    private ListQuestion questions;

    public QuestionService() {
        questions = new ListQuestion();

        try (BufferedReader reader = new BufferedReader(new FileReader("BancoDePreguntasreal.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String questionText = parts[0];
                String[] options = parts[1].split(",");
                Byte correctPos = Byte.parseByte(parts[2]);
                String id = parts[3];

                Question question = new Question(questionText, Arrays.asList(options), correctPos, id);
                questions.addQuestion(question);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId) throws KidsException{
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            throw new KidsException("No se encontro la id");
        }
    }
    public String updateQuestion(String questionId,Question updatedQuestion) throws KidsException{
        try {
            questions.updateQuestion(questionId,updatedQuestion);
            return "Pregunta actualizada";
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }
    public List<Question> getAll(){
        return questions.getAll();
    }
    public Question getQuestionById(String questionId) throws KidsException{
        try {
            return questions.getQuestionById(questionId);
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }
}

