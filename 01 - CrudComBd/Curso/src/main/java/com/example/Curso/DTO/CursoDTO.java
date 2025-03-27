package com.example.Curso.DTO;

public class CursoDTO {

    private String nome;
    private int numeroSala;
    private Long idProfessor;


    public CursoDTO(String nome, int numeroSala, Long idProfessor) {
        this.nome = nome;
        this.numeroSala = numeroSala;
        this.idProfessor = idProfessor;
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

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }
}
