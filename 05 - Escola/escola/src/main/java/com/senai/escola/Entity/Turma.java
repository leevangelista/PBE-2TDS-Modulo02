package com.senai.escola.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sigla;
    private int numeroSala;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idProfessor", referencedColumnName = "id")
    private Professor professor;

    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;
}
