package com.senai.Usuario.Service;

import com.senai.Usuario.DTO.UsuarioDTO;
import com.senai.Usuario.Entity.Usuario;
import com.senai.Usuario.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1 - buscar todos
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // 7 - Buscar usuário com base no nome:
    public List<Usuario> getAllByNome(String nome){
        return usuarioRepository.findAllByNome(nome);
    }

    // 8 - Buscar com base na data de nascimento
    public List<Usuario> getAllByCpf(String cpf){
        return usuarioRepository.findAllByCpf(cpf);
    }
    // 2 - buscar por id
    public Optional<UsuarioDTO> getById(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){ // verifica se foi encontrado algum usuário
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            return Optional.of(usuarioDTO.fromUsuario(usuarioOptional.get())); // se encontrou transforma a Entidade em DTO para retornar a tela
        }else { // se nao encontrou retorna um objeto Optinal vazio
            return Optional.empty();
        }
    }

    // 3 - cadastrar usuário
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toUsuario();
        usuario = usuarioRepository.save(usuario);
        return usuarioDTO.fromUsuario(usuario);
    }

    // 4 - Atualizar dados do usuário menos usuário e senha
    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setSobrenome(usuarioDTO.getSobrenome());
            usuario.setCpf(usuarioDTO.getCpf());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());

            usuario = usuarioRepository.save(usuario);

            return Optional.of(usuarioDTO.fromUsuario(usuario));
        }else{
            return Optional.empty();
        }
    }

    // 6 - Atualizar senha
    public Optional<UsuarioDTO> updateUsuarioSenha(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.setSenha(usuarioDTO.getSenha());
            usuario = usuarioRepository.save(usuario);

            return Optional.of(usuarioDTO.fromUsuario(usuario));
        }else{
            return Optional.empty();
        }
    }

    // 5 - Deletar usuário
    public boolean delete(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
