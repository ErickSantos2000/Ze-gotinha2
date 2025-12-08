package com.example.demo.model;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Frasco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeDose;
    private boolean emEstoque;

    // reprenta 1:N, um frasco tem varias doses
    @OneToMany(mappedBy = "frasco", orphanRemoval = true)
    private List<Dose> dosesDiponiveis;

    public Frasco(){

    }

    public Frasco(String nomeDose){
        this.nomeDose = nomeDose;
        this.dosesDiponiveis = new ArrayList<>();
        emEstoque = true;
    }

}
