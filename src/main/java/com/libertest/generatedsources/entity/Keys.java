/*
 * This file is generated by jOOQ.
 */
package com.libertest.generatedsources.entity;


import com.libertest.generatedsources.entity.tables.Categorias;
import com.libertest.generatedsources.entity.tables.Ingredientes;
import com.libertest.generatedsources.entity.tables.Metadados;
import com.libertest.generatedsources.entity.tables.Receitas;
import com.libertest.generatedsources.entity.tables.records.CategoriasRecord;
import com.libertest.generatedsources.entity.tables.records.IngredientesRecord;
import com.libertest.generatedsources.entity.tables.records.MetadadosRecord;
import com.libertest.generatedsources.entity.tables.records.ReceitasRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CategoriasRecord, Integer> IDENTITY_CATEGORIAS = Identities0.IDENTITY_CATEGORIAS;
    public static final Identity<IngredientesRecord, Integer> IDENTITY_INGREDIENTES = Identities0.IDENTITY_INGREDIENTES;
    public static final Identity<MetadadosRecord, Integer> IDENTITY_METADADOS = Identities0.IDENTITY_METADADOS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CategoriasRecord> KEY_CATEGORIAS_PRIMARY = UniqueKeys0.KEY_CATEGORIAS_PRIMARY;
    public static final UniqueKey<IngredientesRecord> KEY_INGREDIENTES_PRIMARY = UniqueKeys0.KEY_INGREDIENTES_PRIMARY;
    public static final UniqueKey<MetadadosRecord> KEY_METADADOS_PRIMARY = UniqueKeys0.KEY_METADADOS_PRIMARY;
    public static final UniqueKey<ReceitasRecord> KEY_RECEITAS_PRIMARY = UniqueKeys0.KEY_RECEITAS_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CategoriasRecord, ReceitasRecord> FK_CAT_RECEITA = ForeignKeys0.FK_CAT_RECEITA;
    public static final ForeignKey<IngredientesRecord, ReceitasRecord> FK_ING_RECEITA = ForeignKeys0.FK_ING_RECEITA;
    public static final ForeignKey<MetadadosRecord, ReceitasRecord> FK_META_RECEITA = ForeignKeys0.FK_META_RECEITA;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CategoriasRecord, Integer> IDENTITY_CATEGORIAS = Internal.createIdentity(Categorias.CATEGORIAS, Categorias.CATEGORIAS.ID);
        public static Identity<IngredientesRecord, Integer> IDENTITY_INGREDIENTES = Internal.createIdentity(Ingredientes.INGREDIENTES, Ingredientes.INGREDIENTES.ID);
        public static Identity<MetadadosRecord, Integer> IDENTITY_METADADOS = Internal.createIdentity(Metadados.METADADOS, Metadados.METADADOS.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CategoriasRecord> KEY_CATEGORIAS_PRIMARY = Internal.createUniqueKey(Categorias.CATEGORIAS, "KEY_categorias_PRIMARY", Categorias.CATEGORIAS.ID);
        public static final UniqueKey<IngredientesRecord> KEY_INGREDIENTES_PRIMARY = Internal.createUniqueKey(Ingredientes.INGREDIENTES, "KEY_ingredientes_PRIMARY", Ingredientes.INGREDIENTES.ID);
        public static final UniqueKey<MetadadosRecord> KEY_METADADOS_PRIMARY = Internal.createUniqueKey(Metadados.METADADOS, "KEY_metadados_PRIMARY", Metadados.METADADOS.ID);
        public static final UniqueKey<ReceitasRecord> KEY_RECEITAS_PRIMARY = Internal.createUniqueKey(Receitas.RECEITAS, "KEY_receitas_PRIMARY", Receitas.RECEITAS.NOME);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<CategoriasRecord, ReceitasRecord> FK_CAT_RECEITA = Internal.createForeignKey(com.libertest.generatedsources.entity.Keys.KEY_RECEITAS_PRIMARY, Categorias.CATEGORIAS, "FK_CAT_RECEITA", Categorias.CATEGORIAS.NOME_RECEITA);
        public static final ForeignKey<IngredientesRecord, ReceitasRecord> FK_ING_RECEITA = Internal.createForeignKey(com.libertest.generatedsources.entity.Keys.KEY_RECEITAS_PRIMARY, Ingredientes.INGREDIENTES, "FK_ING_RECEITA", Ingredientes.INGREDIENTES.NOME_RECEITA);
        public static final ForeignKey<MetadadosRecord, ReceitasRecord> FK_META_RECEITA = Internal.createForeignKey(com.libertest.generatedsources.entity.Keys.KEY_RECEITAS_PRIMARY, Metadados.METADADOS, "FK_META_RECEITA", Metadados.METADADOS.NOME_RECEITA);
    }
}
