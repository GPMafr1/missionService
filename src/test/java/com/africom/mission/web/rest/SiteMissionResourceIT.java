package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.SiteMission;
import com.africom.mission.repository.SiteMissionRepository;
import com.africom.mission.service.SiteMissionService;
import com.africom.mission.service.dto.SiteMissionDTO;
import com.africom.mission.service.mapper.SiteMissionMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.africom.mission.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SiteMissionResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SiteMissionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_HEURE_DEBUT_REEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_DEBUT_REEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_HEURE_FIN_REEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_FIN_REEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SiteMissionRepository siteMissionRepository;

    @Autowired
    private SiteMissionMapper siteMissionMapper;

    @Autowired
    private SiteMissionService siteMissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSiteMissionMockMvc;

    private SiteMission siteMission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SiteMission createEntity(EntityManager em) {
        SiteMission siteMission = new SiteMission()
            .code(DEFAULT_CODE)
            .commentaire(DEFAULT_COMMENTAIRE)
            .dateHeureDebutReel(DEFAULT_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(DEFAULT_DATE_HEURE_FIN_REEL);
        return siteMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SiteMission createUpdatedEntity(EntityManager em) {
        SiteMission siteMission = new SiteMission()
            .code(UPDATED_CODE)
            .commentaire(UPDATED_COMMENTAIRE)
            .dateHeureDebutReel(UPDATED_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(UPDATED_DATE_HEURE_FIN_REEL);
        return siteMission;
    }

    @BeforeEach
    public void initTest() {
        siteMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiteMission() throws Exception {
        int databaseSizeBeforeCreate = siteMissionRepository.findAll().size();
        // Create the SiteMission
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);
        restSiteMissionMockMvc.perform(post("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isCreated());

        // Validate the SiteMission in the database
        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeCreate + 1);
        SiteMission testSiteMission = siteMissionList.get(siteMissionList.size() - 1);
        assertThat(testSiteMission.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSiteMission.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testSiteMission.getDateHeureDebutReel()).isEqualTo(DEFAULT_DATE_HEURE_DEBUT_REEL);
        assertThat(testSiteMission.getDateHeureFinReel()).isEqualTo(DEFAULT_DATE_HEURE_FIN_REEL);
    }

    @Test
    @Transactional
    public void createSiteMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteMissionRepository.findAll().size();

        // Create the SiteMission with an existing ID
        siteMission.setId(1L);
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteMissionMockMvc.perform(post("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteMission in the database
        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = siteMissionRepository.findAll().size();
        // set the field null
        siteMission.setCode(null);

        // Create the SiteMission, which fails.
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);


        restSiteMissionMockMvc.perform(post("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isBadRequest());

        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateHeureDebutReelIsRequired() throws Exception {
        int databaseSizeBeforeTest = siteMissionRepository.findAll().size();
        // set the field null
        siteMission.setDateHeureDebutReel(null);

        // Create the SiteMission, which fails.
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);


        restSiteMissionMockMvc.perform(post("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isBadRequest());

        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateHeureFinReelIsRequired() throws Exception {
        int databaseSizeBeforeTest = siteMissionRepository.findAll().size();
        // set the field null
        siteMission.setDateHeureFinReel(null);

        // Create the SiteMission, which fails.
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);


        restSiteMissionMockMvc.perform(post("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isBadRequest());

        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSiteMissions() throws Exception {
        // Initialize the database
        siteMissionRepository.saveAndFlush(siteMission);

        // Get all the siteMissionList
        restSiteMissionMockMvc.perform(get("/api/site-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siteMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].dateHeureDebutReel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_DEBUT_REEL))))
            .andExpect(jsonPath("$.[*].dateHeureFinReel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_FIN_REEL))));
    }
    
    @Test
    @Transactional
    public void getSiteMission() throws Exception {
        // Initialize the database
        siteMissionRepository.saveAndFlush(siteMission);

        // Get the siteMission
        restSiteMissionMockMvc.perform(get("/api/site-missions/{id}", siteMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(siteMission.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.dateHeureDebutReel").value(sameInstant(DEFAULT_DATE_HEURE_DEBUT_REEL)))
            .andExpect(jsonPath("$.dateHeureFinReel").value(sameInstant(DEFAULT_DATE_HEURE_FIN_REEL)));
    }
    @Test
    @Transactional
    public void getNonExistingSiteMission() throws Exception {
        // Get the siteMission
        restSiteMissionMockMvc.perform(get("/api/site-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiteMission() throws Exception {
        // Initialize the database
        siteMissionRepository.saveAndFlush(siteMission);

        int databaseSizeBeforeUpdate = siteMissionRepository.findAll().size();

        // Update the siteMission
        SiteMission updatedSiteMission = siteMissionRepository.findById(siteMission.getId()).get();
        // Disconnect from session so that the updates on updatedSiteMission are not directly saved in db
        em.detach(updatedSiteMission);
        updatedSiteMission
            .code(UPDATED_CODE)
            .commentaire(UPDATED_COMMENTAIRE)
            .dateHeureDebutReel(UPDATED_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(UPDATED_DATE_HEURE_FIN_REEL);
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(updatedSiteMission);

        restSiteMissionMockMvc.perform(put("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isOk());

        // Validate the SiteMission in the database
        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeUpdate);
        SiteMission testSiteMission = siteMissionList.get(siteMissionList.size() - 1);
        assertThat(testSiteMission.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSiteMission.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testSiteMission.getDateHeureDebutReel()).isEqualTo(UPDATED_DATE_HEURE_DEBUT_REEL);
        assertThat(testSiteMission.getDateHeureFinReel()).isEqualTo(UPDATED_DATE_HEURE_FIN_REEL);
    }

    @Test
    @Transactional
    public void updateNonExistingSiteMission() throws Exception {
        int databaseSizeBeforeUpdate = siteMissionRepository.findAll().size();

        // Create the SiteMission
        SiteMissionDTO siteMissionDTO = siteMissionMapper.toDto(siteMission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSiteMissionMockMvc.perform(put("/api/site-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteMission in the database
        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiteMission() throws Exception {
        // Initialize the database
        siteMissionRepository.saveAndFlush(siteMission);

        int databaseSizeBeforeDelete = siteMissionRepository.findAll().size();

        // Delete the siteMission
        restSiteMissionMockMvc.perform(delete("/api/site-missions/{id}", siteMission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SiteMission> siteMissionList = siteMissionRepository.findAll();
        assertThat(siteMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
