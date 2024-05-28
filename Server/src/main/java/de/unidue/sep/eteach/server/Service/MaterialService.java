package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Material;
import de.unidue.sep.eteach.server.Repositories.MaterialRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    MaterialRepo materialRepo;

    @Autowired
    public MaterialService(MaterialRepo materialRepo) {
        this.materialRepo = materialRepo;
    }

    public Material getMaterialById(Integer id) {
        return materialRepo.findMaterialById(id);
    }

    public Material createMaterial(Material material) {
        return materialRepo.save(material);
    }

    public List<Material> getAll() {
        return materialRepo.findAll();
    }

    public Material editMaterial(Material materialEditiert) {
        return materialRepo.save(materialEditiert);
    }

    public Material deleteMaterial(Integer id) {
        Material material = materialRepo.findMaterialById(id);
        materialRepo.delete(material);
        return material;
    }
}
