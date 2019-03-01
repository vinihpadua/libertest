package com.libertest.receitasms.handler;

import com.libertest.receitasms.dao.ReceitaRepository;
import com.libertest.receitasms.dto.Filtro;
import com.libertest.receitasms.dto.MensagensValidacao;
import com.libertest.receitasms.dto.Receita;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReceitasHandler {

    private ReceitaRepository receitaRepository;

    @Autowired
    public ReceitasHandler(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public void criarReceita(Receita receita) throws Exception {
        //Verifica se a receita já está salva (Verificação por nome)
        List<Receita> receitasSalvas = receitaRepository.recuperarReceitas(new Filtro(receita.getNome()));

        if(receitasSalvas.size() == 0){
            //Caso ainda não esteja salva, insere o registro no banco
            receitaRepository.criarReceita(receita);
        } else {
            throw new Exception(MensagensValidacao.ERRO_HEADER.getMensagem() + receita.getNome() + MensagensValidacao.CRIAR_ERRO.getMensagem());
        }
    }

    public void atualizarReceita(Receita receita) throws Exception {
        //Verifica se a receita já está salva (Verificação por nome)
        List<Receita> receitasSalvas = receitaRepository.recuperarReceitas(new Filtro(receita.getNome()));

        if(receitasSalvas.size() > 0){
            //Atualiza a receita caso exista algum registro anterior com mesmo nome no banco
            receitaRepository.atualizarReceita(receita);
        } else {
            throw new Exception(MensagensValidacao.ERRO_HEADER.getMensagem() + receita.getNome() + MensagensValidacao.ATUALIZAR_ERRO.getMensagem());
        }
    }

    public List<Receita> filtrarReceitas(String nomeReceita, String listaIngredientes, String categoria) {
        List<String> ingArray = new ArrayList<>();
        //A lista de ingredientes é uma string separada por vírgula
        if(listaIngredientes != null && !listaIngredientes.isEmpty()){
            ingArray = Arrays.asList(listaIngredientes.split(","));
        }

        return receitaRepository.recuperarReceitas(new Filtro(nomeReceita, ingArray,  categoria));
    }

    public void deletarReceita(String nomeReceita) throws Exception {
        //Verifica se a receita já está salva (Verificação por nome)
        List<Receita> receitasSalvas = receitaRepository.recuperarReceitas(new Filtro(nomeReceita));

        if(receitasSalvas.size() > 0){
            //Deleta a receita caso exista algum registro anterior com mesmo nome no banco
            receitaRepository.deletarReceita(nomeReceita);
        } else {
            throw new Exception(MensagensValidacao.ERRO_HEADER.getMensagem() + nomeReceita + MensagensValidacao.DELETAR_ERRO.getMensagem());
        }
    }

}