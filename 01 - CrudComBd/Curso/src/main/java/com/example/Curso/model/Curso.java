package com.example.Curso.model;

import java.util.ArrayList;

public class Curso {
    private int idCurso;
    private String nome;
    private int numeroSala;
    private ArrayList<Aluno> alunos;
    private Professor professor;

    public Curso(Professor professor, ArrayList<Aluno> alunos, int numeroSala, String nome, int idCurso) {
        this.professor = professor;
        this.alunos = alunos;
        this.numeroSala = numeroSala;
        this.nome = nome;
        this.idCurso = idCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
