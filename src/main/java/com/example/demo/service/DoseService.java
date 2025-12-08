package com.example.demo.service;

import com.example.demo.model.Dose;
import com.example.demo.model.Frasco;
import com.example.demo.repository.DoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;

    // criar doses
    public Dose criarDose(String nome, Frasco frasco){

        return doseRepository.save(new Dose(nome, frasco));// faz persistencia
    }

    public void atualizarStatus(Dose dose){
        dose.setAplicada(true); // atualiza na memoria
        doseRepository.save(dose); // atualiza no banco de dados
    }

}
