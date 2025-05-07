package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.SST;
import com.africom.mission.repository.SSTRepository;
import com.africom.mission.service.SSTService;
import com.africom.mission.service.dto.SSTDTO;
import com.africom.mission.service.mapper.SSTMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.africom.mission.domain.enumeration.ImportanceSST;
/**
 * Integration tests for the {@link SSTResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SSTResourceIT {

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ImportanceSST DEFAULT_IMPORTANCE = ImportanceSST.FAIBLE;
    private static final ImportanceSST UPDATED_IMPORTANCE = ImportanceSST.MOYENNE;

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    @Autowired
    private SSTRepository sSTRepository;

    @Autowired
    private SSTMapper sSTMapper;

    @Autowired
    private SSTService sSTService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSSTMockMvc;

    private SST sST;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SST createEntity(EntityManager em) {
        SST sST = new SST()
            .label(DEFAULT_LABEL)
            .date(DEFAULT_DATE)
            .importance(DEFAULT_IMPORTANCE)
            .commentaire(DEFAULT_COMMENTAIRE);
        return sST;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SST createUpdatedEntity(EntityManager em) {
        SST sST = new SST()
            .label(UPDATED_LABEL)
            .date(UPDATED_DATE)
            .importance(UPDATED_IMPORTANCE)
            .commentaire(UPDATED_COMMENTAIRE);
        return sST;
    }

    @BeforeEach
    public void initTest() {
        sST = createEntity(em);
    }

    @Test
    @Transactional
    public void createSST() throws Exception {
        int databaseSizeBeforeCreate = sSTRepository.findAll().size();
        // Create the SST
        SSTDTO sSTDTO = sSTMapper.toDto(sST);
        restSSTMockMvc.perform(post("/api/ssts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sSTDTO)))
            .andExpect(status().isCreated());

        // Validate the SST in the database
        List<SST> sSTList = sSTRepository.findAll();
        assertThat(sSTList).hasSize(databaseSizeBeforeCreate + 1);
        SST testSST = sSTList.get(sSTList.size() - 1);
        assertThat(testSST.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testSST.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSST.getImportance()).isEqualTo(DEFAULT_IMPORTANCE);
        assertThat(testSST.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void createSSTWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sSTRepository.findAll().size();

        // Create the SST with an existing ID
        sST.setId(1L);
        SSTDTO sSTDTO = sSTMapper.toDto(sST);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSSTMockMvc.perform(post("/api/ssts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sSTDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SST in the database
        List<SST> sSTList = sSTRepository.findAll();
        assertThat(sSTList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSSTS() throws Exception {
        // Initialize the database
        sSTRepository.saveAndFlush(sST);

        // Get all the sSTList
        restSSTMockMvc.perform(get("/api/ssts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sST.getId().intValue())))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].importance").value(hasItem(DEFAULT_IMPORTANCE.toString())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)));
    }
    
    @Test
    @Transactional
    public void getSST() throws Exception {
        // Initialize the database
        sSTRepository.saveAndFlush(sST);

        // Get the sST
        restSSTMockMvc.perform(get("/api/ssts/{id}", sST.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sST.getId().intValue()))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.importance").value(DEFAULT_IMPORTANCE.toString()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE));
    }
    @Test
    @Transactional
    public void getNonExistingSST() throws Exception {
        // Get the sST
        restSSTMockMvc.perform(get("/api/ssts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSST() throws Exception {
        // Initialize the database
        sSTRepository.saveAndFlush(sST);

        int databaseSizeBeforeUpdate = sSTRepository.findAll().size();

        // Update the sST
        SST updatedSST = sSTRepository.findById(sST.getId()).get();
        // Disconnect from session so that the updates on updatedSST are not directly saved in db
        em.detach(updatedSST);
        updatedSST
            .label(UPDATED_LABEL)
            .date(UPDATED_DATE)
            .importance(UPDATED_IMPORTANCE)
            .commentaire(UPDATED_COMMENTAIRE);
        SSTDTO sSTDTO = sSTMapper.toDto(updatedSST);

        restSSTMockMvc.perform(put("/api/ssts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sSTDTO)))
            .andExpect(status().isOk());

        // Validate the SST in the database
        List<SST> sSTList = sSTRepository.findAll();
        assertThat(sSTList).hasSize(databaseSizeBeforeUpdate);
        SST testSST = sSTList.get(sSTList.size() - 1);
        assertThat(testSST.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testSST.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSST.getImportance()).isEqualTo(UPDATED_IMPORTANCE);
        assertThat(testSST.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingSST() throws Exception {
        int databaseSizeBeforeUpdate = sSTRepository.findAll().size();

        // Create the SST
        SSTDTO sSTDTO = sSTMapper.toDto(sST);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSSTMockMvc.perform(put("/api/ssts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sSTDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SST in the database
        List<SST> sSTList = sSTRepository.findAll();
        assertThat(sSTList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSST() throws Exception {
        // Initialize the database
        sSTRepository.saveAndFlush(sST);

        int databaseSizeBeforeDelete = sSTRepository.findAll().size();

        // Delete the sST
        restSSTMockMvc.perform(delete("/api/ssts/{id}", sST.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SST> sSTList = sSTRepository.findAll();
        assertThat(sSTList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
