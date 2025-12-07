package com.example.demo.service;

import com.example.demo.model.Dose;

public class DoseService {
    public Dose criarDose(String nome){
        return new Dose(nome);
    }
}
