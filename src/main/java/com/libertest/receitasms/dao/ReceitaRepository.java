package com.libertest.receitasms.dao;

import com.libertest.receitasms.dto.Filtro;
import com.libertest.receitasms.dto.Metadado;
import com.libertest.receitasms.dto.Receita;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.libertest.generatedsources.entity.tables.Receitas.RECEITAS;
import static com.libertest.generatedsources.entity.tables.Categorias.CATEGORIAS;
import static com.libertest.generatedsources.entity.tables.Ingredientes.INGREDIENTES;
import static com.libertest.generatedsources.entity.tables.Metadados.METADADOS;

@Repository
@Transactional
public class ReceitaRepository {

    private final DSLContext dslContext;
    private CategoriaRepository categoriaRepository;
    private IngredienteRepository ingredienteRepository;
    private MetadadoRepository metadadoRepository;

    @Autowired
    public ReceitaRepository(DSLContext dslContext, CategoriaRepository categoriaRepository,
                             IngredienteRepository ingredienteRepository, MetadadoRepository metadadoRepository) {
        this.dslContext = dslContext;
        this.categoriaRepository = categoriaRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.metadadoRepository = metadadoRepository;
    }

    public void criarReceita(Receita receita) {
        this.dslContext.insertInto(RECEITAS)
                .set(RECEITAS.NOME, receita.getNome())
                .set(RECEITAS.MODO_DE_PREPARO, receita.getModoDePreparo())
                .returning(RECEITAS.NOME)
                .fetchOne();

        categoriaRepository.inserirListaCategoria(receita.getCategorias(), receita.getNome());
        ingredienteRepository.inserirIngredientes(receita.getIngredientes(), receita.getNome());
        metadadoRepository.inserirMetadado(receita.getMetadado(), receita.getNome());
    }

    public void atualizarReceita(Receita receita) {
        this.dslContext.update(RECEITAS)
                .set(RECEITAS.NOME, receita.getNome())
                .set(RECEITAS.MODO_DE_PREPARO, receita.getModoDePreparo())
                .where(RECEITAS.NOME.equalIgnoreCase(receita.getNome()))
                .execute();

        categoriaRepository.atualizarCategorias(receita.getCategorias(), receita.getNome());
        ingredienteRepository.atualizarIngredientes(receita.getIngredientes(), receita.getNome());
        metadadoRepository.atualizarMetadado(receita.getMetadado(), receita.getNome());
    }

    public void deletarReceita(String nomeReceita) {
        ingredienteRepository.deletarIngredientesPorReceita(nomeReceita);
        categoriaRepository.deletarCategoriaPorReceita(nomeReceita);
        metadadoRepository.deletarMetadadoPorReceita(nomeReceita);

        this.dslContext.delete(RECEITAS)
                .where(RECEITAS.NOME.equalIgnoreCase(nomeReceita))
                .execute();
    }

    public List<Receita> recuperarReceitas(Filtro filtro) {
        SelectWhereStep select = this.dslContext.selectFrom(RECEITAS
                .join(INGREDIENTES).on(INGREDIENTES.NOME_RECEITA.equalIgnoreCase(RECEITAS.NOME))
                .join(METADADOS).on(METADADOS.NOME_RECEITA.equalIgnoreCase(RECEITAS.NOME))
                .join(CATEGORIAS).on(CATEGORIAS.NOME_RECEITA.equalIgnoreCase(RECEITAS.NOME)));

        filtrarRequisicao(filtro, select);

        Map<Record, Result<Record>> result = select.fetch().intoGroups(RECEITAS);

        if (result != null && !result.isEmpty()) {
            return mapearObjeto(result);
        }

        return new ArrayList<>();
    }


    private void filtrarRequisicao(Filtro filtro, SelectWhereStep select) {
        //Constroi a query dado o filtro passado como parâmetro
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            select.where(RECEITAS.NOME.equalIgnoreCase(filtro.getNome()));
        } else if ((filtro.getCategoria() != null && !filtro.getCategoria().isEmpty())
                || (filtro.getIngredientes() != null && !filtro.getIngredientes().isEmpty())) {

            List<String> receitasNomes = new ArrayList<>();
            if(filtro.getCategoria() != null && !filtro.getCategoria().isEmpty()) {
                //Seleciona os nomes da receita com a categoria
                receitasNomes.addAll(categoriaRepository.selecionarNomeReceitaPorCategoria(filtro));
            }
            if(filtro.getIngredientes() != null && !filtro.getIngredientes().isEmpty()) {
                //Seleciona os nomes da receita com os ingredientes
                receitasNomes.addAll(ingredienteRepository.selecionarNomeReceitaPorIngrediente(filtro));
            }
            //Seleciona pela lista de nomes retornados conforme o filtro
            select.where(RECEITAS.NOME.upper().in(receitasNomes));
        }
    }

    private static List<Receita> mapearObjeto(Map<Record, Result<Record>> receitas) {
        final List<Receita> list = new ArrayList<>();
        for (Map.Entry<Record, Result<Record>> registro : receitas.entrySet()) {
            list.add(mapearReceita(registro));
        }
        return list;
    }

    private static Receita mapearReceita(Map.Entry<Record, Result<Record>> receitaReg) {
        //Necessário para conseguir coletar os valores das tabelas relacionadas à receita
        final Record receitaRec = receitaReg.getKey();
        final Result<Record> values = receitaReg.getValue();

        Receita receita = new Receita();
        receita.setNome(receitaRec.getValue(RECEITAS.NOME));
        receita.setModoDePreparo(receitaRec.getValue(RECEITAS.MODO_DE_PREPARO));

        //mapear Ingredientes
        Set<String> ingredientes = values.stream().map(e -> {
            if (e.getValue(INGREDIENTES.ID) != null) {
                //Modo simples de não adicionar registros duplos que são retornados pela query
                return e.getValue(INGREDIENTES.INGREDIENTE);
            } else return null;
        }).collect(Collectors.toSet());
        receita.setIngredientes(new ArrayList<>(ingredientes));

        //mapear Categorias
        Set<String> categorias = values.stream().map(e -> {
            //Modo simples de não adicionar registros duplos que são retornados pela query
            if (e.getValue(CATEGORIAS.ID) != null) {
                return e.getValue(CATEGORIAS.CATEGORIA);
            } else return null;
        }).collect(Collectors.toSet());
        receita.setCategorias(new ArrayList<>(categorias));

        //mapear Metadados
        final List<Integer> idsMet = new ArrayList<>();
        List<Metadado> metadados = values.stream().map(e -> {
            //Modo simples de não adicionar registros duplos que são retornados pela query
            if (e.getValue(METADADOS.ID) != null && !idsMet.contains(e.getValue(METADADOS.ID))) {
                idsMet.add(e.getValue(METADADOS.ID));
                final Metadado metadado = new Metadado();
                metadado.setTempoDePreparoMinutos(e.getValue(METADADOS.TEMPO_PREP));
                metadado.setRendimentoPorcao(e.getValue(METADADOS.REND_PORCAO));
                metadado.setObservacoes(e.getValue(METADADOS.OBSERVACOES));
                return metadado;
            } else return null;
        }).collect(Collectors.toList());
        //Cada receita contém somente um metadado, logo só o primeiro registro deve ser
        receita.setMetadado(metadados.get(0));

        return receita;
    }
}