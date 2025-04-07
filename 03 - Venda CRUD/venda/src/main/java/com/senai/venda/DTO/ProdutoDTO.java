package com.senai.venda.DTO;

import com.senai.venda.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Objeto DTO é usado para transportar dados entre as camadas.
//Seus atributos que ficaram expostos para o usuário enviar os dados.
//Seus atributos também são utilizados para retornar as informações para os usuários.
//Evitando a assim que deixe a entidade do banco de dados exposta na API.
//Assim também nesta classe que podemos colocar as valicões dos dados da API.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private Long id;
    private String nome;
    private Double valor;
    private int saldo;
    private int saldoMinimo;

    // converte os PodutoDTO em Produto
    // Conversão é necessária para depois das valições e regras de neogico aplicada no DTO transforme para Entidade(Produto) para salvar os dados corretos no banco
    public Produto toProduto(){
        return new Produto(
                this.id,
                this.nome,
                this.valor,
                this.saldo,
                this.saldoMinimo
        );
    }

    // converte Produto em ProdutoDTO
    // Conversão necessária porque o usuário não tenha contato com a Entidade do banco de dados, assim matemos a segurança do sistema.
    public ProdutoDTO fromProduto(Produto produto){
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getSaldo(),
                produto.getSaldoMinimo()
        );
    }
}
