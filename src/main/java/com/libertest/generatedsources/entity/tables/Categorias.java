/*
 * This file is generated by jOOQ.
 */
package com.libertest.generatedsources.entity.tables;


import com.libertest.generatedsources.entity.DefaultSchema;
import com.libertest.generatedsources.entity.Indexes;
import com.libertest.generatedsources.entity.Keys;
import com.libertest.generatedsources.entity.tables.records.CategoriasRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Categorias extends TableImpl<CategoriasRecord> {

    private static final long serialVersionUID = 550541148;

    /**
     * The reference instance of <code>categorias</code>
     */
    public static final Categorias CATEGORIAS = new Categorias();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CategoriasRecord> getRecordType() {
        return CategoriasRecord.class;
    }

    /**
     * The column <code>categorias.ID</code>.
     */
    public final TableField<CategoriasRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>categorias.NOME_RECEITA</code>.
     */
    public final TableField<CategoriasRecord, String> NOME_RECEITA = createField("NOME_RECEITA", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>categorias.CATEGORIA</code>.
     */
    public final TableField<CategoriasRecord, String> CATEGORIA = createField("CATEGORIA", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>categorias</code> table reference
     */
    public Categorias() {
        this(DSL.name("categorias"), null);
    }

    /**
     * Create an aliased <code>categorias</code> table reference
     */
    public Categorias(String alias) {
        this(DSL.name(alias), CATEGORIAS);
    }

    /**
     * Create an aliased <code>categorias</code> table reference
     */
    public Categorias(Name alias) {
        this(alias, CATEGORIAS);
    }

    private Categorias(Name alias, Table<CategoriasRecord> aliased) {
        this(alias, aliased, null);
    }

    private Categorias(Name alias, Table<CategoriasRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Categorias(Table<O> child, ForeignKey<O, CategoriasRecord> key) {
        super(child, key, CATEGORIAS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CATEGORIAS_FK_CAT_RECEITA, Indexes.CATEGORIAS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CategoriasRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CATEGORIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CategoriasRecord> getPrimaryKey() {
        return Keys.KEY_CATEGORIAS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CategoriasRecord>> getKeys() {
        return Arrays.<UniqueKey<CategoriasRecord>>asList(Keys.KEY_CATEGORIAS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CategoriasRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CategoriasRecord, ?>>asList(Keys.FK_CAT_RECEITA);
    }

    public Receitas receitas() {
        return new Receitas(this, Keys.FK_CAT_RECEITA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categorias as(String alias) {
        return new Categorias(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categorias as(Name alias) {
        return new Categorias(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Categorias rename(String name) {
        return new Categorias(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Categorias rename(Name name) {
        return new Categorias(name, null);
    }
}