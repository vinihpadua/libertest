package com.libertest.receitasms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Receita {
    private String nome;
    private List<String> ingredientes;
    private String modoDePreparo;
    private Metadado metadado;
    private List<String> categorias;

    public Receita() {
    }

    public Receita(String nome, List<String> ingredientes, String modoDePreparo, Metadado metadado, List<String> categorias) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.metadado = metadado;
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public Metadado getMetadado() {
        return metadado;
    }

    public void setMetadado(Metadado metadado) {
        this.metadado = metadado;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categoria) {
        this.categorias = categoria;
    }
}
