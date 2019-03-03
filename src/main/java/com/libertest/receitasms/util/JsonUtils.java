package com.libertest.receitasms.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.libertest.receitasms.dto.MensagensValidacao;
import com.libertest.receitasms.dto.Receita;

import java.io.IOException;

public class JsonUtils {

    private JsonUtils() {
    }

    public static Receita validarJsonReceita(String json) throws Exception {
        // Converte o payload de receita, caso exista um campo inválido, um erro é lançado
        Receita receita = converterJsonReceita(json);

        // Valida os campos obrigatórios
        if(receita.getNome() == null || receita.getNome().isEmpty()){
            throw new Exception(MensagensValidacao.NOME_REQUIRED.getMensagem());
        }
        if(receita.getModoDePreparo() == null || receita.getModoDePreparo().isEmpty()){
            throw new Exception(MensagensValidacao.MODOPREP_REQUIRED.getMensagem());
        }
        if(receita.getCategorias() == null || receita.getCategorias().isEmpty()){
            throw new Exception(MensagensValidacao.CATEG_REQUIRED.getMensagem());
        }
        if(receita.getIngredientes() == null || receita.getIngredientes().isEmpty()){
            throw new Exception(MensagensValidacao.INGRED_REQUIRED.getMensagem());
        }
        if(receita.getMetadado() == null){
            throw new Exception(MensagensValidacao.METADADO_REQUIRED.getMensagem());

        } else if (receita.getMetadado().getTempoDePreparoMinutos() == null){
            throw new Exception(MensagensValidacao.TEMPOPREP_REQUIRED.getMensagem());

        } else if (receita.getMetadado().getRendimentoPorcao() == null){
            throw new Exception(MensagensValidacao.RENDIM_REQUIRED.getMensagem());
        }

        return receita;
    }

    private static Receita converterJsonReceita(String json) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, Receita.class);
    }
}