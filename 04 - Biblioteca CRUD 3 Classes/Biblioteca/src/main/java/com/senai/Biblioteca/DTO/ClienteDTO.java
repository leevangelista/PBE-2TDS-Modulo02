package com.senai.Biblioteca.DTO;

import com.senai.Biblioteca.Entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;

    public Cliente toCliente(){
        return new Cliente(
                this.id,
                this.nome,
                this.sobrenome,
                this.cpf
        );
    }

    public ClienteDTO fromCliente(Cliente cliente){
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getSobrenome()
        );
    }
}
