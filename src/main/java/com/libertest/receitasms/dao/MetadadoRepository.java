package com.libertest.receitasms.dao;

import com.libertest.receitasms.dto.Metadado;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.libertest.generatedsources.entity.tables.Metadados.METADADOS;

@Repository
@Transactional
public class MetadadoRepository {

    private final DSLContext dslContext;

    @Autowired
    public MetadadoRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void inserirMetadado(Metadado metadado, String nomeReceita) {
        this.dslContext.insertInto(METADADOS)
                .columns(METADADOS.NOME_RECEITA,
                        METADADOS.REND_PORCAO,
                        METADADOS.TEMPO_PREP,
                        METADADOS.OBSERVACOES)
                .values(nomeReceita,
                        metadado.getRendimentoPorcao(),
                        metadado.getTempoDePreparoMinutos(),
                        metadado.getObservacoes())
                .onDuplicateKeyIgnore().execute();
    }

    public void deletarMetadadoPorReceita(String nomeReceita) {
        this.dslContext.delete(METADADOS)
                .where(METADADOS.NOME_RECEITA.eq(nomeReceita))
                .execute();
    }

    public void atualizarMetadado(Metadado metadado, String nomeReceita) {
        deletarMetadadoPorReceita(nomeReceita);
        inserirMetadado(metadado, nomeReceita);
    }

}