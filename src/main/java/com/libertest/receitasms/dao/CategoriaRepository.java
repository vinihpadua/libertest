package com.libertest.receitasms.dao;

import com.libertest.generatedsources.entity.tables.records.CategoriasRecord;
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

import static com.libertest.generatedsources.entity.tables.Categorias.CATEGORIAS;

@Repository
@Transactional
public class CategoriaRepository {

    private final DSLContext dslContext;

    @Autowired
    public CategoriaRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void inserirListaCategoria(List<String> categorias, String nomeReceita) {
        for(String categoria : categorias) {
            this.dslContext.insertInto(CATEGORIAS)
                    .columns(CATEGORIAS.NOME_RECEITA,
                            CATEGORIAS.CATEGORIA)
                    .values(nomeReceita, categoria)
                    .onDuplicateKeyIgnore().execute();
        }
    }

    public List<String> selecionarNomeReceitaPorCategoria(Filtro filtro) {
        //Método para retornar a lista de nomes de receitas que contém a categoria passada pelo filtro
        final Result<CategoriasRecord> records = this.dslContext.selectFrom(CATEGORIAS)
                .where(CATEGORIAS.CATEGORIA.equalIgnoreCase(filtro.getCategoria()))
                .fetchInto(CATEGORIAS);

        if (records != null && !records.isEmpty()) {
            Set<String> nomes = records.stream().map(e -> {
                if (e.getValue(CATEGORIAS.NOME_RECEITA) != null) {
                    //Modo simples de não adicionar registros duplos que são retornados pela query
                    return e.getValue(CATEGORIAS.NOME_RECEITA).toUpperCase();
                } else return null;
            }).collect(Collectors.toSet());

            return new ArrayList<>(nomes);
        }

        return new ArrayList<>();
    }

    public void deletarCategoriaPorReceita(String nomeReceita) {
        this.dslContext.delete(CATEGORIAS)
                .where(CATEGORIAS.NOME_RECEITA.eq(nomeReceita))
                .execute();
    }

    public void atualizarCategorias(List<String> categorias, String nomeReceita) {
        deletarCategoriaPorReceita(nomeReceita);
        inserirListaCategoria(categorias, nomeReceita);
    }

}