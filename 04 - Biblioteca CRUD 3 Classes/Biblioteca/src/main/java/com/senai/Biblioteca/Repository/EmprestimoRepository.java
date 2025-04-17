package com.senai.Biblioteca.Repository;

import com.senai.Biblioteca.Entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
