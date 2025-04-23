package com.senai.Usuario.Repository;

import com.senai.Usuario.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByNome(String nome);

    List<Usuario> getAllByCpf(String cpf);
}
