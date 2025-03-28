package com.example.Curso.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessor;
    private String nome;
    private String cpf;

    // cascade = cascadeType.ALL faz com que quando um professor for deletado o curso também seja deletado
    @OneToOne(mappedBy = "professor", cascade = CascadeType.ALL)
    private Curso curso;

    public Professor() {
    }

    public Professor(Long idProfessor, String nome, String cpf) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
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
}
