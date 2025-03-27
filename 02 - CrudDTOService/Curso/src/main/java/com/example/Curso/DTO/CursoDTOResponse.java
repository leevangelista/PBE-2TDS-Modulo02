package com.example.Curso.DTO;

import com.example.Curso.Entity.Aluno;
import com.example.Curso.Entity.Professor;

import java.util.List;

public class CursoDTOResponse {
    public Long id;
    public String nome;
    public int numeroSala;
    public Professor professor;
    public List<Aluno> alunos;

    public CursoDTOResponse(){

    }

    public CursoDTOResponse(Long id, String nome, int numeroSala, Professor professor, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.numeroSala = numeroSala;
        this.professor = professor;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int setNumeroSala) {
        this.numeroSala = setNumeroSala;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
