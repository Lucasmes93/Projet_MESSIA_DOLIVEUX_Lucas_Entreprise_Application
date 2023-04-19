package com.example.ecoles.ecole;

import com.example.ecoles.classe.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ecoles")
public class EcolesController {
    private final EcolesRepository ecolesRepository;
    private final ClassesRepository classesRepository;

    @Autowired
    public EcolesController(EcolesRepository ecolesRepository, ClassesRepository classesRepository) {
        this.ecolesRepository = ecolesRepository;
        this.classesRepository = classesRepository;
    }


    @PostMapping
    public ResponseEntity<Ecole> addEcole(@RequestBody Ecole ecole) {
        Ecole saveEcole = ecolesRepository.save(ecole);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveEcole.getId()).toUri();

        return ResponseEntity.created(location).body(saveEcole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ecole> updateEcole(@PathVariable Integer id, @RequestBody Ecole ecole){
        Optional<Ecole> optionalEcole = ecolesRepository.findById(id);
        if (!optionalEcole.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        ecole.setId(optionalEcole.get().getId());
        ecolesRepository.save(ecole);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ecole> delete(@PathVariable Integer id) {
        Optional<Ecole> optionalEcole= ecolesRepository.findById(id);
        if (!optionalEcole.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        ecolesRepository.delete(optionalEcole.get());

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ecole> getById(@PathVariable Integer id) {
        Optional<Ecole> optionalEcole = ecolesRepository.findById(id);
        if (!optionalEcole.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEcole.get());
    }
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(ecolesRepository.findAll());

    }
}
