package com.example.ecoles.classe;

import com.example.ecoles.ecole.Ecole;
import com.example.ecoles.ecole.EcolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/classes")
public class ClassesController {
    private final EcolesRepository ecolesRepository;
    private final ClassesRepository classesRepository;
    @Autowired
    public ClassesController(EcolesRepository ecolesRepository, ClassesRepository classesRepository) {
        this.ecolesRepository = ecolesRepository;
        this.classesRepository = classesRepository;
    }


    @PostMapping
    public ResponseEntity<Classe> create(@RequestBody Classe classe) {

        Optional<Ecole> optionalEcole = ecolesRepository.findById(classe.getEcole().getId());
        if (!optionalEcole.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        classe.setEcole(optionalEcole.get());

        Classe savedClasse = classesRepository.save(classe);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClasse.getId()).toUri();

        return ResponseEntity.created(location).body(savedClasse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable Integer id, @RequestBody Classe classe){
        Optional<Ecole> optionalEcole = ecolesRepository.findById(classe.getEcole().getId());
        if (!optionalEcole.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Classe> optionalClasse = classesRepository.findById(id);
        if (!optionalClasse.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        classe.setEcole(optionalEcole.get());
        classe.setId(optionalClasse.get().getId());
        classesRepository.save(classe);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Classe> delete(@PathVariable Integer id) {
        Optional<Classe> optionalClasse= classesRepository.findById(id);
        if (!optionalClasse.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        classesRepository.delete(optionalClasse.get());

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getById(@PathVariable Integer id) {
        Optional<Classe> optionalClasse = classesRepository.findById(id);
        if (!optionalClasse.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalClasse.get());
    }
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(classesRepository.findAll());

    }
    @GetMapping("/ecole_id/{ecole_id}")
    public  ResponseEntity getClassesByEcoleId(@PathVariable Integer ecole_id){
        List<Classe> classesByEcole = classesRepository.findAllByEcole_Id(ecole_id);
        return ResponseEntity.ok(classesByEcole);
    }


}
