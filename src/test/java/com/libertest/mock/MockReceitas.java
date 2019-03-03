package com.libertest.mock;

public class MockReceitas {

    public static String payloadValidoCriarReceita() {
        return  "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao forno\"," +
                "   \"categorias\": [" +
                "       \"molho\", \"salgado\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 120," +
                "       \"rendimentoPorcao\": 4," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadValidoAtualizarReceita() {
        return  "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 10," +
                "       \"rendimentoPorcao\": 2," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaNome() {
        return "{" +
                "   \"nome\": null," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao forno\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 120," +
                "       \"rendimentoPorcao\": 4," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaIngredientes() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": null," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 10," +
                "       \"rendimentoPorcao\": 2," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaModoPreparo() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": null," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 10," +
                "       \"rendimentoPorcao\": 2," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaCategorias() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": null," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 10," +
                "       \"rendimentoPorcao\": 2," +
                "       \"observacoes\": null" +
                "    }" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaMetadado() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": null" +
                "}";
    }

    public static String payloadInvalidoReceitaTempoDePreparo() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": null," +
                "       \"rendimentoPorcao\": 2," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }

    public static String payloadInvalidoReceitaRedimento() {
        return "{" +
                "   \"nome\": \"Molho\"," +
                "   \"ingredientes\": [" +
                "       \"leite\", \"cebola\", \"pimenta\"" +
                "   ]," +
                "   \"modoDePreparo\": \"Bata os ingredientes e leve ao fogao\"," +
                "   \"categorias\": [" +
                "       \"pf\", \"doce\"]," +
                "   \"metadado\": {" +
                "       \"tempoDePreparoMinutos\": 10," +
                "       \"rendimentoPorcao\": null," +
                "       \"observacoes\": null" +
                "    }" +
                "}";
    }


}