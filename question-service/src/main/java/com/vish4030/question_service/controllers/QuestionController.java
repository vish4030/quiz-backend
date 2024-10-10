package com.vish4030.question_service.controllers;

import com.vish4030.question_service.entities.Question;
import com.vish4030.question_service.entities.QuestionWrapper;
import com.vish4030.question_service.entities.Responses;
import com.vish4030.question_service.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    QuestionService questionservice;

    public QuestionController() {
    }

    @Autowired
    public QuestionController(QuestionService questionservice) {
        this.questionservice = questionservice;
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionservice.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionservice.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionservice.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQuestions){
        return questionservice.getQuestionForQuiz(category, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsId){
        return questionservice.getQuestions(questionsId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responses> responses){
        return questionservice.getScore(responses);
    }
}
