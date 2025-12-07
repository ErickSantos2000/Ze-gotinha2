package com.example.demo.service;

import com.example.demo.model.Dose;
import com.example.demo.model.Frasco;
import com.example.demo.repository.FrascoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrascoService {
    @Autowired
    FrascoRepository frascoRepository;
    private static final int dosesPorFrascos = 5;
    @Autowired
    private DoseService doseService;

    public FrascoService(DoseService doseService){
        this.doseService = doseService;
    }

    // criação de frascos
    public Frasco criarFrascos(String nomeDose){
        Frasco frasco = new Frasco(nomeDose);
        frascoRepository.save(frasco); // faz persistencia

        for (int i = 0; i < dosesPorFrascos; i++){
            frasco.getDosesDiponiveis().add(doseService.criarDose(nomeDose, frasco));
        }
        return frasco; // faz persistencia
    }
}


