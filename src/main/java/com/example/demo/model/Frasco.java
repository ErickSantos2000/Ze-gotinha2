package com.example.demo.model;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Frasco {
    private int id;
    private List<Dose> dosesDiponiveis;
    public Frasco(){
        this.dosesDiponiveis = new ArrayList<>();
    }

}
