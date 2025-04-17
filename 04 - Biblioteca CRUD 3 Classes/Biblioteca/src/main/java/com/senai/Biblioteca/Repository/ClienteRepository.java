package com.senai.Biblioteca.Repository;

import com.senai.Biblioteca.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
