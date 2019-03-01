package com.libertest.receitasms.dao;

import com.libertest.generatedsources.entity.tables.records.IngredientesRecord;
import com.libertest.receitasms.dto.Filtro;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.libertest.generatedsources.entity.tables.Ingredientes.INGREDIENTES;

@Repository
@Transactional
public class IngredienteRepository {

    private final DSLContext dslContext;

    @Autowired
    public IngredienteRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void inserirIngredientes(List<String> ingredientes, String nomeReceita) {
        for(String ingrediente : ingredientes){
            this.dslContext.insertInto(INGREDIENTES)
                    .columns(INGREDIENTES.NOME_RECEITA,
                            INGREDIENTES.INGREDIENTE)
                    .values(nomeReceita, ingrediente)
                    .onDuplicateKeyIgnore().execute();
        }
    }

    public List<String> selecionarNomeReceitaPorIngrediente(Filtro filtro) {
        //Método para retornar a lista de nomes de receitas que contém os ingredientes passado pelo filtro
        final Result<IngredientesRecord> records = this.dslContext.selectFrom(INGREDIENTES)
                .where(INGREDIENTES.INGREDIENTE.upper().in(filtro.getIngredientesUpper()))
                .fetchInto(INGREDIENTES);

        if (records != null && !records.isEmpty()) {
            Set<String> nomes = records.stream().map(e -> {
                if (e.getValue(INGREDIENTES.NOME_RECEITA) != null) {
                    //Modo simples de não adicionar registros duplos que são retornados pela query
                    return e.getValue(INGREDIENTES.NOME_RECEITA).toUpperCase();
                } else return null;
            }).collect(Collectors.toSet());

            return new ArrayList<>(nomes);
        }

        return new ArrayList<>();
    }

    public void deletarIngredientesPorReceita(String nomeReceita) {
        this.dslContext.delete(INGREDIENTES)
                .where(INGREDIENTES.NOME_RECEITA.eq(nomeReceita))
                .execute();
    }

    public void atualizarIngredientes(List<String> ingredientes, String nomeReceita) {
        deletarIngredientesPorReceita(nomeReceita);
        inserirIngredientes(ingredientes, nomeReceita);
    }

}