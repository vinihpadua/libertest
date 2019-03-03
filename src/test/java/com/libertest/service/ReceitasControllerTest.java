package com.libertest.service;

import com.libertest.generatedsources.entity.tables.records.ReceitasRecord;
import com.libertest.receitasms.dao.CategoriaRepository;
import com.libertest.receitasms.dao.IngredienteRepository;
import com.libertest.receitasms.dao.MetadadoRepository;
import com.libertest.receitasms.dao.ReceitaRepository;
import com.libertest.receitasms.dto.MensagensValidacao;
import com.libertest.receitasms.dto.Receita;
import com.libertest.receitasms.handler.ReceitasHandler;
import com.libertest.receitasms.service.ReceitasController;
import com.libertest.receitasms.util.JsonUtils;
import com.libertest.mock.MockReceitas;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static com.libertest.generatedsources.entity.tables.Receitas.RECEITAS;
import static com.libertest.generatedsources.entity.tables.Ingredientes.INGREDIENTES;
import static com.libertest.generatedsources.entity.tables.Categorias.CATEGORIAS;
import static com.libertest.generatedsources.entity.tables.Metadados.METADADOS;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DSLContext.class, Result.class, InsertResultStep.class})
@PowerMockIgnore("javax.management.*")
public class ReceitasControllerTest {

    @Mock
    private DSLContext dslContext = Mockito.mock(DSLContext.class, Mockito.RETURNS_DEEP_STUBS);

    @Mock
    private InsertResultStep<ReceitasRecord> receitasRecordInsertResultStep;

    @Mock
    private Result<ReceitasRecord> receitasRecords;

    @InjectMocks
    private CategoriaRepository categoriaRepository = new CategoriaRepository(dslContext);

    @InjectMocks
    private IngredienteRepository ingredienteRepository = new IngredienteRepository(dslContext);

    @InjectMocks
    private MetadadoRepository metadadoRepository = new MetadadoRepository(dslContext);

    @InjectMocks
    private ReceitaRepository receitaRepository = new ReceitaRepository(dslContext, categoriaRepository, ingredienteRepository, metadadoRepository);

    @InjectMocks
    private ReceitasHandler receitasHandler = new ReceitasHandler(receitaRepository);

    @InjectMocks
    private ReceitasController receitasController = new ReceitasController(receitasHandler);

    @Before
    public void init() {
        PowerMockito.spy(DSLContext.class);

        PowerMockito.spy(Result.class);
        mockStatic(Result.class);

        PowerMockito.spy(InsertResultStep.class);
        mockStatic(InsertResultStep.class);
    }

    @Test
    public void criarReceitaValida() throws Exception {
        Receita receita = JsonUtils.validarJsonReceita(MockReceitas.payloadValidoCriarReceita());
        mockInserirReceita(receita);

        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadValidoCriarReceita());
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(MensagensValidacao.CRIAR_SUCESSO.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaNomeNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaNome());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.NOME_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaIngredientesNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaIngredientes());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.INGRED_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaModoPreparoNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaModoPreparo());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.MODOPREP_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaCategoriaNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaCategorias());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.CATEG_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaMetadadoNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaMetadado());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.METADADO_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaTempoPreparoNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaTempoDePreparo());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.TEMPOPREP_REQUIRED.getMensagem(), response.getBody());
    }

    @Test
    public void criarReceitaRendimentoNull() {
        ResponseEntity<String> response = receitasController.criarReceita(MockReceitas.payloadInvalidoReceitaRedimento());
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        assertEquals(MensagensValidacao.RENDIM_REQUIRED.getMensagem(), response.getBody());
    }

    private void mockInserirReceita(Receita receita) {
        final ReceitasRecord receitasRecord = new ReceitasRecord();
        receitasRecord.setNome(receita.getNome());

        BDDMockito.given(dslContext.select(RECEITAS.fields()).from(RECEITAS)
                .where(RECEITAS.NOME.eq(receita.getNome()))
                .fetchInto(RECEITAS)).willReturn(null);

        when(dslContext.insertInto(RECEITAS)
                .set(RECEITAS.NOME, receita.getNome())
                .set(RECEITAS.MODO_DE_PREPARO, receita.getModoDePreparo())
                .returning(RECEITAS.NOME)).thenReturn(receitasRecordInsertResultStep);

        BDDMockito.given(receitasRecordInsertResultStep.fetchOne()).willReturn(receitasRecord);

        mockInserirMetadados(receita);
        mockInserirIngredientes(receita);
        mockInserirCategorias(receita);
    }

    private void mockInserirMetadados(final Receita receita) {
        BDDMockito.given(dslContext.insertInto(
                METADADOS).columns(
                METADADOS.ID,
                METADADOS.NOME_RECEITA,
                METADADOS.REND_PORCAO,
                METADADOS.TEMPO_PREP,
                METADADOS.OBSERVACOES).values(
                1, receita.getNome(), receita.getMetadado().getRendimentoPorcao(),
                receita.getMetadado().getTempoDePreparoMinutos(), receita.getMetadado().getObservacoes())
                .onDuplicateKeyIgnore().execute()).willReturn(1);
    }

    private void mockInserirIngredientes(final Receita receita) {
        for(int i = 0; i < receita.getIngredientes().size(); i++) {
            BDDMockito.given(dslContext.insertInto(
                    INGREDIENTES).columns(
                    INGREDIENTES.ID,
                    INGREDIENTES.NOME_RECEITA,
                    INGREDIENTES.INGREDIENTE).values(
                    1, receita.getNome(), receita.getIngredientes().get(i))
                    .onDuplicateKeyIgnore().execute()).willReturn(1);
        }
    }

    private void mockInserirCategorias(final Receita receita) {
        for(int i = 0; i < receita.getCategorias().size(); i++ ) {
            BDDMockito.given(dslContext.insertInto(
                    CATEGORIAS).columns(
                    CATEGORIAS.ID,
                    CATEGORIAS.NOME_RECEITA,
                    CATEGORIAS.CATEGORIA).values(
                    1, receita.getNome(), receita.getCategorias().get(i))
                    .onDuplicateKeyIgnore().execute()).willReturn(1);
        }
    }


}