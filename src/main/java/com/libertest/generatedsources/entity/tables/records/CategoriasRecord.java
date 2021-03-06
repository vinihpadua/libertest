/*
 * This file is generated by jOOQ.
 */
package com.libertest.generatedsources.entity.tables.records;


import com.libertest.generatedsources.entity.tables.Categorias;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class CategoriasRecord extends UpdatableRecordImpl<CategoriasRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 1044254253;

    /**
     * Setter for <code>categorias.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>categorias.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>categorias.NOME_RECEITA</code>.
     */
    public void setNomeReceita(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>categorias.NOME_RECEITA</code>.
     */
    public String getNomeReceita() {
        return (String) get(1);
    }

    /**
     * Setter for <code>categorias.CATEGORIA</code>.
     */
    public void setCategoria(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>categorias.CATEGORIA</code>.
     */
    public String getCategoria() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Categorias.CATEGORIAS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Categorias.CATEGORIAS.NOME_RECEITA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Categorias.CATEGORIAS.CATEGORIA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNomeReceita();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCategoria();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNomeReceita();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCategoria();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriasRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriasRecord value2(String value) {
        setNomeReceita(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriasRecord value3(String value) {
        setCategoria(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriasRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoriasRecord
     */
    public CategoriasRecord() {
        super(Categorias.CATEGORIAS);
    }

    /**
     * Create a detached, initialised CategoriasRecord
     */
    public CategoriasRecord(Integer id, String nomeReceita, String categoria) {
        super(Categorias.CATEGORIAS);

        set(0, id);
        set(1, nomeReceita);
        set(2, categoria);
    }
}
