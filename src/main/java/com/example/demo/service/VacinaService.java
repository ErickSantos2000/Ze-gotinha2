package com.example.demo.service;
import java.util.*;

import com.example.demo.model.Dose;
import com.example.demo.model.Frasco;
import com.example.demo.model.Pessoa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service // informa ao Spring que ele deve criar e gerenciar um objeto(um "bean") desta classe
// quando o Spring gerencia um objeto, ele pode injeta-lo em outras classes que precisam dele,
    public class VacinaService {
        private Queue<Pessoa> fila;
        private Deque<Frasco> pilha;
        private FrascoService frascoService;
        private PessoaService pessoaService;
        private DoseService doseService;

        @Autowired // faz a injeção de dependencias
        public VacinaService(FrascoService frascoService, PessoaService pessoaService, DoseService doseService){
            this.pilha = new ArrayDeque<>();
            this.fila = new ArrayDeque<>();
            this.frascoService = frascoService;
            this.pessoaService = pessoaService;
            this.doseService = doseService;
        }

    // metodo para aplicar as doses
    public void aplicarDose(Frasco frasco){

        List<Dose> doses = frasco.getDosesDiponiveis();
        for (int i = 0; i < doses.size(); i++) {
            Dose dose = doses.get(i);
            if (!dose.getAplicada()) {
                dose.setAplicada(true);
                doses.remove(i);
                break;
            }
        }
    }

    // metodo para vacinar pessoas
    public void vacinarPessoa(){
        if(pilha.isEmpty()){
            System.out.println("Pilha de Frascos esta vazia! Ninguém pode ser vacinado.");
            return;
        }
        if (fila.isEmpty()){
            System.out.println("Fila de pessoas esta vazia! Ninguém para vacinar.");
            return;
        }

        // pega o frasco que esta no topo da pilha
        Frasco topo = pilha.peek();
        Pessoa pessoaInicio = fila.peek();

        if(!topo.getDosesDiponiveis().isEmpty()){ // verificar se o frasco contem doses
            this.aplicarDose(topo); // aplica vacina e remove apos isso
            System.out.println("Vacina aplicada em " + pessoaInicio.getNome() + "!");
            this.removerPessoaFIla(); // remove pessoa da fila

            if (topo.getDosesDiponiveis().isEmpty()){ // caso apos a aplicação o frasco estiver vazio, ele sera removido
                this.desimpilhaFrasco();
                System.out.println("Frasco de " + topo.getNomeDose() + " esgotado. Removendo da pilha.");
            }
        }
    }

    public void adicionarPessoaFila(String nome, String cpf, int idade, boolean vacinada){
            if(fila.size() == 15){
                System.out.println("Limite maximo de pessoas atigindos!");
            }

            fila.add(pessoaService.criarPessoa(nome, cpf, idade, vacinada));
    }

    public void removerPessoaFIla(){
        fila.poll();
    }

    // empilhar frascos
    public boolean empilharFrasco(String nomeDose){
        if(pilha.size() == 3){
            System.out.println("A pilha esta cheia!");
            return false;
        }
        else {
            pilha.push(frascoService.criarFrascos(nomeDose));
            return true;
        }
    }

    // desempilhar frasco
    public boolean desimpilhaFrasco(){
        if(pilha.isEmpty()){
            System.out.println("A pilha esta vazia!");
            return false;
        }
        else {
            pilha.pop();
            return true;
        }
    }
}
