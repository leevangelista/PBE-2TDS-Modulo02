package com.senai.venda.service;

import com.senai.venda.DTO.ProdutoDTO;
import com.senai.venda.entity.Produto;
import com.senai.venda.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// classe service onde fica todas as regras de negócio e faz a comunição com o banco de dados(repository)
@Service
public class ProdutoService {

    // Comunicação entre service e a próxima cada que é a repository
    @Autowired
    private ProdutoRepository produtoRepository;

    // buscar todos os produtos.
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    // buscar com base no Id
    // retorna sempre um objeto Optional, para que depois se possa fazer a validação do retorno.
    public Optional<ProdutoDTO> getById(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            return Optional.of(produtoDTO.fromProduto(produtoOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    // salva no banco de dados um produto
    // Para isso é necessário transformar o DTO em entidade
    // o retorno sempre será um DTO
    public ProdutoDTO createProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoDTO.toProduto();
        produto = produtoRepository.save(produto);
        return produtoDTO.fromProduto(produto);
    }

    // atualiza os dados com base no id e o objeto DTO recebido.
    // busca o objeto que será atualizado
    // atualiza os dados, caso encontrar, com base no DTO recebido.
    public Optional<ProdutoDTO> updateProduto(Long id, ProdutoDTO produtoDTO){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            produto.setNome(produtoDTO.getNome());
            produto.setValor(produtoDTO.getValor());
            produto.setSaldo(produtoDTO.getSaldo());
            produto.setSaldoMinimo(produtoDTO.getSaldoMinimo());

            produto = produtoRepository.save(produto);

            return Optional.of(produtoDTO.fromProduto(produto));
        }else{
            return Optional.empty();
        }
    }

    // deleta com base no id recebido.
    public boolean delete(Long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
