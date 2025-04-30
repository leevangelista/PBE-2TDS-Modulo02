package com.senai.escola.Repository;

import com.senai.escola.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAllByNome(String nome);
}
