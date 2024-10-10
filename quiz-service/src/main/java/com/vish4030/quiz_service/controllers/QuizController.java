package com.vish4030.quiz_service.controllers;

import com.vish4030.quiz_service.entities.QuestionWrapper;
import com.vish4030.quiz_service.entities.QuizDto;
import com.vish4030.quiz_service.entities.Responses;
import com.vish4030.quiz_service.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    QuizService quizService;

    public QuizController() {
    }

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @PostMapping("generate")
    public ResponseEntity<List<QuestionWrapper>> generateQuizQuestion(@RequestBody List<Integer> questionsId){
        return quizService.generateQuizQuestion(questionsId);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> getQuizScore(@RequestBody List<Responses> res){
        return  quizService.getQuizScore(res);
    }
}
