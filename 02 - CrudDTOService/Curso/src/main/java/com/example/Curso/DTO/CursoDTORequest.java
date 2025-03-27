package com.example.Curso.DTO;

import com.example.Curso.Entity.Professor;

public class CursoDTORequest {

    private String nome;
    private int numeroSala;
    private Professor professor;

    public CursoDTORequest(){
    }

    public CursoDTORequest(String nome, int numeroSala, Professor professor) {
        this.nome = nome;
        this.numeroSala = numeroSala;
        this.professor = professor;
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

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
