package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Freundschaft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreundschaftRepo extends JpaRepository<Freundschaft, Integer>  {
    Freundschaft findFreundschaftById(Integer id);
    List<Freundschaft> findAll();
}

