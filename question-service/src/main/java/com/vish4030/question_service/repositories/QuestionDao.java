package com.vish4030.question_service.repositories;

import com.vish4030.question_service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    // Custom native query example
    @Query(value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestions(String category, int numQuestions);
}
