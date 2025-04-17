package com.senai.Biblioteca.Service;

import com.senai.Biblioteca.DTO.ClienteDTO;
import com.senai.Biblioteca.DTO.EmprestimoDTO;
import com.senai.Biblioteca.Entity.Cliente;
import com.senai.Biblioteca.Entity.Emprestimo;
import com.senai.Biblioteca.Repository.ClienteRepository;
import com.senai.Biblioteca.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> getAllEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Optional<EmprestimoDTO> getById(Long id){
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        if(emprestimoOptional.isPresent()){
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
            return Optional.of(emprestimoDTO.fromEmprestimo(emprestimoOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = emprestimoDTO.toEmprestimo();
        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimoDTO.fromEmprestimo(emprestimo);
    }

    public Optional<EmprestimoDTO> update(Long id, EmprestimoDTO emprestimoDTO){
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        if(emprestimoOptional.isPresent()){
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setDataInicial(emprestimoDTO.getDataInicial());
            emprestimo.setDataFinal(emprestimoDTO.getDataFinal());
            emprestimo.setCliente(emprestimoDTO.getCliente());
            emprestimo.setLivros(emprestimoDTO.getLivros());

            emprestimo = emprestimoRepository.save(emprestimo);

            return Optional.of(emprestimoDTO.fromEmprestimo(emprestimo));
        }else{
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(emprestimoRepository.existsById(id)){
            emprestimoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
