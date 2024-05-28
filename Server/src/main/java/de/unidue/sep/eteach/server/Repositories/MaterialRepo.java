package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer>  {
    Material findMaterialById(Integer id);
    List<Material> findAll();
}

