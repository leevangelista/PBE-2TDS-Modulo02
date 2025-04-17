package com.senai.Biblioteca.Repository;

import com.senai.Biblioteca.Entity.Emprestimo;
import com.senai.Biblioteca.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByNome(String nome); // buscar com base no nome
}
