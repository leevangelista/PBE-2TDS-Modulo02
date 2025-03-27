package com.example.Curso.Repository;

import com.example.Curso.Entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
