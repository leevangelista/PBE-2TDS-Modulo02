package com.senai.venda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os metodos get/setter
@AllArgsConstructor // cria construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio (necessário para o Hibernet)
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // adiciona o auto-incremento ao id, assim não precisa e nem deve ser enviado o ID via requisição
    private Long id;

    private String nome;
    private Double valor;
    private int saldo;
    private int saldoMinimo;
}
