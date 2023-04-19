package com.example.ecoles.classe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordsController {
    private final ClassesRepository classesRepository;
    @Autowired
    public KeywordsController( ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }
    @GetMapping
    public ResponseEntity getAllByPageNiveau(@RequestParam(required = false) String niveau, @RequestParam(required = false) String name){
        if (niveau != null){
            List<Classe> pageClasseNiveau = classesRepository.findAllByNiveau(niveau);
            return ResponseEntity.ok(pageClasseNiveau);
        }
        if (name != null){
            List<Classe> pageClasseName = classesRepository.findAllByName(name);
            return ResponseEntity.ok(pageClasseName);

        }
        return (ResponseEntity) ResponseEntity.noContent();
    }
}
