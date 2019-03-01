package com.libertest.receitasms.dto;

public enum MensagensValidacao {

    NOME_REQUIRED("Campo nome é obrigatório"),
    INGRED_REQUIRED("Campo ingredientes é obrigatório e deve conter pelo menos um ingrediente"),
    MODOPREP_REQUIRED("Campo modoDePreparo é obrigatório"),
    CATEG_REQUIRED("Campo categorias é obrigatório e deve conter pelo menos uma categoria"),
    METADADO_REQUIRED("Campo metadado é obrigatório"),
    TEMPOPREP_REQUIRED("Campo tempoDePreparoMinutos é obrigatório"),
    RENDIM_REQUIRED("Campo rendimentoPorcao é obrigatório"),
    ERRO_HEADER("Receita com o nome "),
    CRIAR_SUCESSO("Receita salva com sucesso"),
    CRIAR_ERRO(" já foi inserida no banco de dados"),
    ATUALIZAR_SUCESSO("Receita atualizada com sucesso"),
    ATUALIZAR_ERRO(" não foi localizada no banco de dados para ser atualizada"),
    DELETAR_SUCESSO("Receita deletada com sucesso"),
    DELETAR_ERRO(" não foi localizada no banco de dados para ser deletada");

    private String mensagem;

    MensagensValidacao(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}