package com.example.httpCode.Banco;

import com.example.httpCode.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBd {
    private List<User> users;

    public UserBd(){
        this.users = new ArrayList<>();
    }

    // busca todos os usuário
    public List<User> findAll(){
        return new ArrayList<>(users);
    }

    // busca um user
    public User getById(int id){
        return users.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // insere o usuário
    public User insert(User user){
        users.add(user);
        return user;
    }

    // atualiza o usuário
    public boolean update(User userBd, User user){
        userBd.setNome(user.getNome());
        return true;
    }


    public boolean delete(User user){
        users.remove(user);
        return true;
    }

}
