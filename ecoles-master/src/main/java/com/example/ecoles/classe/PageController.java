package com.example.ecoles.classe;

import com.example.ecoles.ecole.EcolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pages")
public class PageController {
    private final ClassesRepository classesRepository;
    @Autowired
    public PageController( ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }
    @GetMapping("/{number}")
    public ResponseEntity getAllByPage(@PathVariable Integer number){
        Pageable pageable = PageRequest.of(number, 4);
        Page<Classe> pageClasse = classesRepository.findAll(pageable);
        return ResponseEntity.ok(pageClasse);
    }

}
