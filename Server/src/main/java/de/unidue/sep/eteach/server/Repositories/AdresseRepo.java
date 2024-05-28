package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepo extends JpaRepository<Adresse, Integer>  {
    Adresse findAdresseById(Integer id);
    List<Adresse> findAll();
}

