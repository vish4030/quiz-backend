package com.vish4030.quiz_service.services;

import com.vish4030.quiz_service.entities.QuestionWrapper;
import com.vish4030.quiz_service.entities.Quiz;
import com.vish4030.quiz_service.entities.Responses;
import com.vish4030.quiz_service.feign.QuizInterface;
import com.vish4030.quiz_service.repositories.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    QuizDao quizDao;

    QuizInterface quizInterface;



    public QuizService() {
    }

    @Autowired
    public QuizService(QuizDao quizDao, QuizInterface quizInterface) {
        this.quizDao = quizDao;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(categoryName, numQuestions).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsId(questions);
        System.out.println(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> generateQuizQuestion(List<Integer> questionsId) {

        List<QuestionWrapper> temp = quizInterface.getQuestions(questionsId).getBody();
        System.out.println(temp);
        return new ResponseEntity<>(temp,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getQuizScore(List<Responses> res) {
        int result = quizInterface.getScore(res).getBody();

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
