package com.example.Curso.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;
    private String nome;
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "idCurso", referencedColumnName = "idCurso")
    @JsonIgnoreProperties("alunos")
    private Curso curso;

    public Aluno() {
    }

    public Aluno(String cpf, String nome, Long idAluno) {
        this.cpf = cpf;
        this.nome = nome;
        this.idAluno = idAluno;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
