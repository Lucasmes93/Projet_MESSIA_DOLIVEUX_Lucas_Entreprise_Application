package com.example.ecoles.classe;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ClassesRepository extends JpaRepository<Classe, Integer> {
    List<Classe> findAllById(Integer id, Pageable pageable);
    List<Classe> findAllByEcole_Id(Integer ecole_id);
    List<Classe> findAllByNiveau(String niveau);
    List<Classe> findAllByName(String name);
}
