package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoasRepository pessoasRepository;

    public Pessoa criarPessoa(String nome, String cpf, int idade){
        return pessoasRepository.save(new Pessoa(nome, cpf, idade)); // faz a pesistencia de dados
    }

    public void atualizarStatus(Pessoa pessoa){
        pessoa.setVacinada(true); // atualiza na memoria
        pessoasRepository.save(pessoa); // atualiza no banco de dados
    }
}
