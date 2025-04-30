package com.senai.escola.DTO;

import com.senai.escola.Entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO implements Serializable {

    private Long id;
    private String nome;
    private String sobrenome;

    public Professor toProfessor(){
        return new Professor(
                this.id,
                this.nome,
                this.sobrenome
        );
    }

    public ProfessorDTO fromProfessor(Professor usuario){
        return new ProfessorDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome()
        );
    }
}
