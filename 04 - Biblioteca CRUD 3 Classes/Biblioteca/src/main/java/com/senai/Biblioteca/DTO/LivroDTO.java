package com.senai.Biblioteca.DTO;

import com.senai.Biblioteca.Entity.Emprestimo;
import com.senai.Biblioteca.Entity.Livro;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO implements Serializable {
    private Long id;
    private String nome;
    private String autor;
    private int ISBN;
    private String genero;

    public Livro toLivro(){
        return new Livro(
                this.id,
                this.nome,
                this.autor,
                this.ISBN,
                this.genero
        );
    }

    public LivroDTO fromLivro(Livro livro){
        return new LivroDTO(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getISBN(),
                livro.getGenero()
        );
    }
}
