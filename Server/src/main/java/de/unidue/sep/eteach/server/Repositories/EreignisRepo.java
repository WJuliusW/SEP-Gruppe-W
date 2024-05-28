package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Ereignis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EreignisRepo extends JpaRepository<Ereignis, Integer>  {
    Ereignis findEreignisById(Integer id);
    List<Ereignis> findAll();
    List<Ereignis> getAllByNutzerEquals(Integer nutzerId);
    List<Ereignis> getAllByVeranstaltungEquals(Integer veranstaltungId);
}

