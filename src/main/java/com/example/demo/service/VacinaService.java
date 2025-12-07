package com.example.demo.service;
import java.util.*;

import com.example.demo.model.Dose;
import com.example.demo.model.Frasco;
import com.example.demo.model.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacinaService {
    private Queue<Pessoa> fila;
    private Deque<Frasco> pilha;
    private FrascoService frascoService;
    private PessoaService pessoaService;

    public VacinaService(FrascoService frascoService, PessoaService pessoaService, Queue<Pessoa> fila, Deque<Frasco> pilha){
        this.pilha = pilha;
        this.fila = fila;
        this.frascoService = frascoService;
        this.pessoaService = pessoaService;
    }

    // metodo para aplicar as doses
    public void aplicarDose(Frasco frasco){

        List<Dose> doses = frasco.getDosesDiponiveis();
        for (int i = 0; i < doses.size(); i++) {
            Dose dose = doses.get(i);
            if (!dose.getAplicada()) {
                dose.setAplicada(true);
                frasco.getDosesDiponiveis().remove(i);
                break;
            }
        }
    }

    // metodo para vacinar pessoas
    public void vacinarPessoa(){
        if(pilha.isEmpty()){
            System.out.println("Pilha de Frascos esta vazia!");
            return;
        }

        // pega o frasco que esta no topo da pilha
        Frasco topo = pilha.peek();
        Pessoa pessoaInicio = fila.peek();

        if(!topo.getDosesDiponiveis().isEmpty()){ // verificar se o frasco contem doses
            this.aplicarDose(topo); // aplica vacina e remove apos isso
            this.removerPessoaFIla(); // remove pessoa da fila
            System.out.println("Vacina aplicada em " + pessoaInicio.getNome() + "!");

            if (topo.getDosesDiponiveis().isEmpty()){ // caso apos a aplicação o frasco estiver vazio, ele sera removido
                this.desimpilhaFrasco();
                System.out.println("Frasco vazio, removendo frasco da pilha...");
            }
        }
    }

    public void adicionarPessoaFila(String nome, String cpf, int idade){
        fila.add(pessoaService.criarPessoa(nome, cpf, idade));
    }

    public void removerPessoaFIla(){
        fila.poll();
    }

    // desimpilhar frascos
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

    // empilhar frasco
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
