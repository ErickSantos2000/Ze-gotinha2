package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private int idade;
    private boolean vacinada;

    public Pessoa(String nome, String cpf, int idade, boolean vacinada){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.vacinada = vacinada;
    }
}
