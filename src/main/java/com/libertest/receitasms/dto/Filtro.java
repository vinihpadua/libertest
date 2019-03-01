package com.libertest.receitasms.dto;

import java.util.List;
import java.util.stream.Collectors;

public class Filtro {
    private String nome;
    private List<String> ingredientes;
    private String categoria;

    public Filtro() {
    }

    public Filtro(String nome) {
        this.nome = nome;
    }

    public Filtro(String nome, List<String> ingredientes, String categoria) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
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

    public List<String> getIngredientesUpper() {
        return this.ingredientes.stream().map(e -> {
            return e.toUpperCase();
        }).collect(Collectors.toList());
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
