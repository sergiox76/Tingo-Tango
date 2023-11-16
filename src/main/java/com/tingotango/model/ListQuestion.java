package com.tingotango.model;

import com.tingotango.exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ListQuestion {
    private List<Question> questionList;

    public ListQuestion(){
        questionList = new ArrayList<>();
    }
    public List<Question> getAll (){
        return questionList;
    }

    public Question getQuestionById (String questionId) throws KidsException {
        for(Question question :questionList){
            if(question.getId().equals(questionId)){
                return question;
            }
        }
        throw new KidsException("No hay una pregunta con esa id");
    }
    public void addQuestion(Question newQuestion){
        questionList.add(newQuestion);
    }
    public boolean deleteQuestion(String questionId){
        return questionList.removeIf(question -> question.getId().equals(questionId));
    }
    public void updateQuestion(String questionId, Question updatedQuestion) throws KidsException{
        boolean matchFound = false;
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getId().equals(questionId)) {
                questionList.set(i, updatedQuestion);
                matchFound = true;
                break;
            }
        }
        if(!matchFound) {
            throw new KidsException("La id es incorrecta o no se encontro");
        }
    }
}
