package com.libertest.receitasms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(nome, receita.nome) &&
                Objects.equals(ingredientes, receita.ingredientes) &&
                Objects.equals(modoDePreparo, receita.modoDePreparo) &&
                Objects.equals(metadado, receita.metadado) &&
                Objects.equals(categorias, receita.categorias);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, ingredientes, modoDePreparo, metadado, categorias);
    }
}
