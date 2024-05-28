package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>  {

    Quiz findQuizById(Integer id);
    List<Quiz> findAll();
}

