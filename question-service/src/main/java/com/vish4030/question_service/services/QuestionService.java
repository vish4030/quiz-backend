package com.vish4030.question_service.services;

import com.vish4030.question_service.entities.Question;
import com.vish4030.question_service.entities.QuestionWrapper;
import com.vish4030.question_service.entities.Responses;
import com.vish4030.question_service.repositories.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    QuestionDao questionDao;

    public QuestionService() {
    }

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question> >getAllQuestion() {
        return new ResponseEntity<>( questionDao.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {

        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestions(category, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionsId) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionsId){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();

            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }

        return  new ResponseEntity<>( wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Responses> responses) {
        int right =0;

        for(Responses res : responses){
            Question question  = questionDao.findById(res.getId()).get();

            if(res.getResponse().equals(question.getRightAnswer()))
                right+=1;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
