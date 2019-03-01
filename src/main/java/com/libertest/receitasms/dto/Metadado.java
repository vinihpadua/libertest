package com.libertest.receitasms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


public class Metadado {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tempoDePreparoMinutos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rendimentoPorcao;
    private String observacoes;

    public Metadado() {
    }

    public Metadado(Integer tempoDePreparoMinutos, Integer rendimentoPorcao, String observacoes) {
        this.tempoDePreparoMinutos = tempoDePreparoMinutos;
        this.rendimentoPorcao = rendimentoPorcao;
        this.observacoes = observacoes;
    }

    public Integer getTempoDePreparoMinutos() {
        return tempoDePreparoMinutos;
    }

    public void setTempoDePreparoMinutos(Integer tempoDePreparoMinutos) {
        this.tempoDePreparoMinutos = tempoDePreparoMinutos;
    }

    public Integer getRendimentoPorcao() {
        return rendimentoPorcao;
    }

    public void setRendimentoPorcao(Integer rendimentoPorcao) {
        this.rendimentoPorcao = rendimentoPorcao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadado metadado = (Metadado) o;
        return Objects.equals(tempoDePreparoMinutos, metadado.tempoDePreparoMinutos) &&
                Objects.equals(rendimentoPorcao, metadado.rendimentoPorcao) &&
                Objects.equals(observacoes, metadado.observacoes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tempoDePreparoMinutos, rendimentoPorcao, observacoes);
    }
}
