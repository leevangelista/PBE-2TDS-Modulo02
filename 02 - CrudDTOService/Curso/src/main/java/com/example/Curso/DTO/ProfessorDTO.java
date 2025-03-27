package com.example.Curso.DTO;

public class ProfessorDTO {

    private Long id;
    private String nome;
    private String cpf;

    public ProfessorDTO(){
    }

    public ProfessorDTO(String nome, String cpf, Long id) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
