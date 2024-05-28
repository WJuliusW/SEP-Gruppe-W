package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Lehrender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LehrenderRepo extends JpaRepository<Lehrender, Integer>  {
    Lehrender findLehrenderById(Integer id);
    List<Lehrender> findAll();
    Lehrender findLehrenderByEmail(String email);
}

