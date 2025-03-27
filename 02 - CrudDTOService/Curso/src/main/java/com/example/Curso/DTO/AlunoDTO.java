package com.example.Curso.DTO;

import com.example.Curso.Entity.Curso;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AlunoDTO {
    private long id;
    private String nome;
    private String cpf;
    @JsonIgnoreProperties("alunos")
    private Curso curso;

    public AlunoDTO(){

    }

    public AlunoDTO(long id, String nome, String cpf, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
