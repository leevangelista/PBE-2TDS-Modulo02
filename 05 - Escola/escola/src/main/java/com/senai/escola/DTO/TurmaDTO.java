package com.senai.escola.DTO;

import com.senai.escola.Entity.Aluno;
import com.senai.escola.Entity.Professor;
import com.senai.escola.Entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO implements Serializable {

    private Long id;
    private String sigla;
    private int numeroSala;
    private String nome;

    private Professor professor;
    private List<Aluno> alunos;

    public Turma toTurma(){
        return new Turma(
                this.id,
                this.sigla,
                this.numeroSala,
                this.nome,
                this.professor,
                this.alunos
        );
    }

    public TurmaDTO fromTurma(Turma usuario){
        return new TurmaDTO(
                usuario.getId(),
                usuario.getSigla(),
                usuario.getNumeroSala(),
                usuario.getNome(),
                usuario.getProfessor(),
                usuario.getAlunos()
        );
    }
}
