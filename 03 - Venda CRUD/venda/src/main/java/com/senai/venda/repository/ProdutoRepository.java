package com.senai.venda.repository;

import com.senai.venda.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

// Todas as consultas e operações com o banco de dados ficam registradas nesta classe
// caso precise de alguma consulta extra que o JPA não tenha por padrão, será criado nesta classe
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
