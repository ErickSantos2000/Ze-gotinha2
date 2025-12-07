package com.example.demo.model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dose {
    private int id;
    private String nome;
    private boolean aplicada;

    public Dose(String nome){
        this.nome = nome;
        this.aplicada = false;
    }

    public  boolean getAplicada(){
        return aplicada;
    }

}
