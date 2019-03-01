package com.libertest.receitasms.service;

import com.libertest.receitasms.dto.MensagensValidacao;
import com.libertest.receitasms.dto.Receita;
import com.libertest.receitasms.handler.ReceitasHandler;
import com.libertest.receitasms.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/1/libertest" )
public class ReceitasController {

    protected ReceitasHandler receitasHandler;

    @Autowired
    public ReceitasController(ReceitasHandler receitasHandler) {
        this.receitasHandler = receitasHandler;
    }

    @CrossOrigin
    @RequestMapping(path = "/criarReceita",  method = RequestMethod.POST)
    public ResponseEntity<String> criarReceita(@RequestBody String receita) {
        //Redireciona o payload da receita a ser criada para o handler de criação
        try {
            receitasHandler.criarReceita(JsonUtils.validarJsonReceita(receita));
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MensagensValidacao.CRIAR_SUCESSO.getMensagem(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/recuperarReceita",  method = RequestMethod.GET)
    public ResponseEntity<List<Receita>> recuperarReceita(String nomeReceita, String categoria, String listaIngredientes) {
        //Redireciona os parâmetros da requisição para o handler responsável
        List<Receita> receitas = receitasHandler.filtrarReceitas(nomeReceita, listaIngredientes, categoria);
        return new ResponseEntity<>(receitas, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/atualizarReceita",  method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarReceita(@RequestBody String receita) {
        //Redireciona o payload da receita a ser atualizada para o handler de atualização
        try {
            receitasHandler.atualizarReceita(JsonUtils.validarJsonReceita(receita));
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MensagensValidacao.ATUALIZAR_SUCESSO.getMensagem(), HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(path = "/deletarReceita",  method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarReceita(@RequestParam String nomeReceita) {
        //Chama o handler de deleção com o nome da receita a ser excluída
        try {
            receitasHandler.deletarReceita(nomeReceita);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MensagensValidacao.DELETAR_SUCESSO.getMensagem(), HttpStatus.OK);
    }

}
