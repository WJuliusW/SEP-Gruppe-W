package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutzerRepo extends JpaRepository<Nutzer,Integer> {
    Nutzer findNutzerById(Integer id);
    Nutzer findNutzerByEmail(String email);
}
