package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private boolean aplicada;

    // Relação N:1 -> cada dose pertence a um frasco
    @ManyToOne
    @JoinColumn(name = "frasco_id")
    private Frasco frasco;

    public Dose() {

    }

    public Dose(String nome, Frasco frasco) {
        this.nome = nome;
        this.aplicada = false;
        this.frasco = frasco;
    }

    public  boolean getAplicada(){
        return aplicada;
    }

}
