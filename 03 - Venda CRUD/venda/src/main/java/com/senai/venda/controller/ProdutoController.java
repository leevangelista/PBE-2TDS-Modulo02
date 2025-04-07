package com.senai.venda.controller;

import com.senai.venda.DTO.ProdutoDTO;
import com.senai.venda.entity.Produto;
import com.senai.venda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// A Controller tem a função de receber as requisições do usuário, processar os dados e retornar as informações ou eventuais erros como resposta.
// Essa responsabilidade é desempenhada por meio de seus métodos e respectivas rotas (endpoints).
// No nosso caso, utilizamos o Spring Boot para facilitar e agilizar esse processo.

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    // Comunicação entre Controller a próxima camada Service.
    @Autowired
    private ProdutoService produtoService;

    // busca todos produtos
    @GetMapping
    public List<Produto> getAll(){
        return produtoService.getAllProdutos();
    }

    // busca apenas um produto
    // função vai receber um Id que será utilizado para realizar a busca
    // função retorna ResponseEntity contendo o objeto DTO.
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id){
        Optional<ProdutoDTO> produtoDTOOptional = produtoService.getById(id);
        if(produtoDTOOptional.isPresent()){
            return ResponseEntity.ok(produtoDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // Save de produto
    // Recebe por meio do body da requisição, um objeto do tipo DTO(ProtudoDTO)
    // Retorna o objeto DTO que foi salvo no banco de dados
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtoDTOSave = produtoService.createProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTOSave);
    }

    // função de atualizar os dados de um produto.
    // Recebe um id por parametro da url e o objeto DTO no body da requisição.
    // Retorna o objeto DTO atualizado para o usuário.
    // Retorna 404 se de erro ao encontrar o objeto para ser atualizado.
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        Optional<ProdutoDTO> produtoDTOOptional = produtoService.updateProduto(id, produtoDTO);
        if(produtoDTOOptional.isPresent()){
            return ResponseEntity.ok(produtoDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // Deleta um produto.
    // Recebe o ID que será utilizado para fazer a busca do produto que será deletado.
    // Retorna o status 204 caso de certo de deletar o produto.
    // Retorna o status 404 caso não tenha conseguido encontrar o objeto para atualizar.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(produtoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
