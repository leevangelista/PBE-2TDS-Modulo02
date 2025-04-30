package com.senai.escola.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senai.escola.Entity.Aluno;
import com.senai.escola.Entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO implements Serializable {

    private Long id;
    private String nome;
    private String cpf;
    @JsonIgnore // json de retorno de uma busca(GET) nao aparece esse atributo
    private Turma turma;

    public Aluno toAluno(){
        return new Aluno(
                this.id,
                this.nome,
                this.cpf,
                this.turma
        );
    }

    public AlunoDTO fromAluno(Aluno usuario){
        return new AlunoDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTurma()
        );
    }
}
