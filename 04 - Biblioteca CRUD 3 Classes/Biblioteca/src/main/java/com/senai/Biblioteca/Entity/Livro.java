package com.senai.Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    @Column(unique = true)
    private int ISBN;
    private String genero;

    @ManyToMany(mappedBy = "livros")
    private Set<Emprestimo> emprestimos;

    public Livro(Long id, String nome, String autor, int ISBN, String genero){
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.ISBN = ISBN;
        this.genero = genero;
    }
}
