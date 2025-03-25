package com.example.Curso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    private int idProfessor;
    private String nome;
    private String cpf;
    private double salario;

}
