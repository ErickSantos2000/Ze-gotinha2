package com.example.demo.model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private int idade;

    public Pessoa(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
}
