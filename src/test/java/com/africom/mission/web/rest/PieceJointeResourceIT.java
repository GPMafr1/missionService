package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.PieceJointe;
import com.africom.mission.repository.PieceJointeRepository;
import com.africom.mission.service.PieceJointeService;
import com.africom.mission.service.dto.PieceJointeDTO;
import com.africom.mission.service.mapper.PieceJointeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PieceJointeResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PieceJointeResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CONTENU = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTENU = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTENU_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTENU_CONTENT_TYPE = "image/png";

    @Autowired
    private PieceJointeRepository pieceJointeRepository;

    @Autowired
    private PieceJointeMapper pieceJointeMapper;

    @Autowired
    private PieceJointeService pieceJointeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPieceJointeMockMvc;

    private PieceJointe pieceJointe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointe createEntity(EntityManager em) {
        PieceJointe pieceJointe = new PieceJointe()
            .nom(DEFAULT_NOM)
            .type(DEFAULT_TYPE)
            .contenu(DEFAULT_CONTENU)
            .contenuContentType(DEFAULT_CONTENU_CONTENT_TYPE);
        return pieceJointe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointe createUpdatedEntity(EntityManager em) {
        PieceJointe pieceJointe = new PieceJointe()
            .nom(UPDATED_NOM)
            .type(UPDATED_TYPE)
            .contenu(UPDATED_CONTENU)
            .contenuContentType(UPDATED_CONTENU_CONTENT_TYPE);
        return pieceJointe;
    }

    @BeforeEach
    public void initTest() {
        pieceJointe = createEntity(em);
    }

    @Test
    @Transactional
    public void createPieceJointe() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeRepository.findAll().size();
        // Create the PieceJointe
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);
        restPieceJointeMockMvc.perform(post("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isCreated());

        // Validate the PieceJointe in the database
        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeCreate + 1);
        PieceJointe testPieceJointe = pieceJointeList.get(pieceJointeList.size() - 1);
        assertThat(testPieceJointe.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPieceJointe.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPieceJointe.getContenu()).isEqualTo(DEFAULT_CONTENU);
        assertThat(testPieceJointe.getContenuContentType()).isEqualTo(DEFAULT_CONTENU_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createPieceJointeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeRepository.findAll().size();

        // Create the PieceJointe with an existing ID
        pieceJointe.setId(1L);
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPieceJointeMockMvc.perform(post("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointe in the database
        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeRepository.findAll().size();
        // set the field null
        pieceJointe.setNom(null);

        // Create the PieceJointe, which fails.
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);


        restPieceJointeMockMvc.perform(post("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isBadRequest());

        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeRepository.findAll().size();
        // set the field null
        pieceJointe.setType(null);

        // Create the PieceJointe, which fails.
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);


        restPieceJointeMockMvc.perform(post("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isBadRequest());

        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPieceJointes() throws Exception {
        // Initialize the database
        pieceJointeRepository.saveAndFlush(pieceJointe);

        // Get all the pieceJointeList
        restPieceJointeMockMvc.perform(get("/api/piece-jointes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pieceJointe.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].contenuContentType").value(hasItem(DEFAULT_CONTENU_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contenu").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTENU))));
    }
    
    @Test
    @Transactional
    public void getPieceJointe() throws Exception {
        // Initialize the database
        pieceJointeRepository.saveAndFlush(pieceJointe);

        // Get the pieceJointe
        restPieceJointeMockMvc.perform(get("/api/piece-jointes/{id}", pieceJointe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pieceJointe.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.contenuContentType").value(DEFAULT_CONTENU_CONTENT_TYPE))
            .andExpect(jsonPath("$.contenu").value(Base64Utils.encodeToString(DEFAULT_CONTENU)));
    }
    @Test
    @Transactional
    public void getNonExistingPieceJointe() throws Exception {
        // Get the pieceJointe
        restPieceJointeMockMvc.perform(get("/api/piece-jointes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePieceJointe() throws Exception {
        // Initialize the database
        pieceJointeRepository.saveAndFlush(pieceJointe);

        int databaseSizeBeforeUpdate = pieceJointeRepository.findAll().size();

        // Update the pieceJointe
        PieceJointe updatedPieceJointe = pieceJointeRepository.findById(pieceJointe.getId()).get();
        // Disconnect from session so that the updates on updatedPieceJointe are not directly saved in db
        em.detach(updatedPieceJointe);
        updatedPieceJointe
            .nom(UPDATED_NOM)
            .type(UPDATED_TYPE)
            .contenu(UPDATED_CONTENU)
            .contenuContentType(UPDATED_CONTENU_CONTENT_TYPE);
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(updatedPieceJointe);

        restPieceJointeMockMvc.perform(put("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isOk());

        // Validate the PieceJointe in the database
        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeUpdate);
        PieceJointe testPieceJointe = pieceJointeList.get(pieceJointeList.size() - 1);
        assertThat(testPieceJointe.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPieceJointe.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPieceJointe.getContenu()).isEqualTo(UPDATED_CONTENU);
        assertThat(testPieceJointe.getContenuContentType()).isEqualTo(UPDATED_CONTENU_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingPieceJointe() throws Exception {
        int databaseSizeBeforeUpdate = pieceJointeRepository.findAll().size();

        // Create the PieceJointe
        PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPieceJointeMockMvc.perform(put("/api/piece-jointes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointe in the database
        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePieceJointe() throws Exception {
        // Initialize the database
        pieceJointeRepository.saveAndFlush(pieceJointe);

        int databaseSizeBeforeDelete = pieceJointeRepository.findAll().size();

        // Delete the pieceJointe
        restPieceJointeMockMvc.perform(delete("/api/piece-jointes/{id}", pieceJointe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PieceJointe> pieceJointeList = pieceJointeRepository.findAll();
        assertThat(pieceJointeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
