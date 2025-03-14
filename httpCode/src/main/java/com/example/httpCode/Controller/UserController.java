package com.example.httpCode.Controller;

import com.example.httpCode.Banco.UserBd;
import com.example.httpCode.Model.User;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserController {

    UserBd repository = new UserBd();

    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<User> getById(int id){
        User user = repository.getById(id);
        if(user != null){
            return ResponseEntity.ok(user); // retorna código 200
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // retorna código 404
//            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> insertBanco(User user){
        User userSave = repository.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    public ResponseEntity<User> update(int id, User user){
        User userBd = repository.getById(id);
        if(userBd == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repository.update(userBd, user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> delete(int id){
        User user = repository.getById(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID: "+ id +" não encontrado");
        }
        repository.delete(user);
        return ResponseEntity.ok("Usuário "+ user.getNome() +" deletado com sucesso");
    }
}
