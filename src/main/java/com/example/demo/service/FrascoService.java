package com.example.demo.service;

import com.example.demo.model.Dose;
import com.example.demo.model.Frasco;

public class FrascoService {
    private static final int dosesPorFrascos = 5;
    private DoseService doseService;

    public FrascoService(DoseService doseService){
        this.doseService = doseService;
    }

    // criação de frascos
    public Frasco criarFrascos(String nomeDose){
    Frasco frasco = new Frasco();

    for (int i = 0; i < dosesPorFrascos; i++){
        frasco.getDosesDiponiveis().add(doseService.criarDose(nomeDose));
        }
        return frasco;
    }

    public void imprimir(Frasco frasco){
        for(Dose dose : frasco.getDosesDiponiveis()){
            System.out.println(dose.getNome());
        }
    }
}


