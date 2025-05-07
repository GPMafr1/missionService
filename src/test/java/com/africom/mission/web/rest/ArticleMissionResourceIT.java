package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.ArticleMission;
import com.africom.mission.repository.ArticleMissionRepository;
import com.africom.mission.service.ArticleMissionService;
import com.africom.mission.service.dto.ArticleMissionDTO;
import com.africom.mission.service.mapper.ArticleMissionMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ArticleMissionResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleMissionResourceIT {

    private static final Integer DEFAULT_QUANTITE_PLANIFIEE = 1;
    private static final Integer UPDATED_QUANTITE_PLANIFIEE = 2;

    private static final Integer DEFAULT_QUANTITE_UTILISEE = 1;
    private static final Integer UPDATED_QUANTITE_UTILISEE = 2;

    @Autowired
    private ArticleMissionRepository articleMissionRepository;

    @Autowired
    private ArticleMissionMapper articleMissionMapper;

    @Autowired
    private ArticleMissionService articleMissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleMissionMockMvc;

    private ArticleMission articleMission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleMission createEntity(EntityManager em) {
        ArticleMission articleMission = new ArticleMission()
            .quantitePlanifiee(DEFAULT_QUANTITE_PLANIFIEE)
            .quantiteUtilisee(DEFAULT_QUANTITE_UTILISEE);
        return articleMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleMission createUpdatedEntity(EntityManager em) {
        ArticleMission articleMission = new ArticleMission()
            .quantitePlanifiee(UPDATED_QUANTITE_PLANIFIEE)
            .quantiteUtilisee(UPDATED_QUANTITE_UTILISEE);
        return articleMission;
    }

    @BeforeEach
    public void initTest() {
        articleMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleMission() throws Exception {
        int databaseSizeBeforeCreate = articleMissionRepository.findAll().size();
        // Create the ArticleMission
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(articleMission);
        restArticleMissionMockMvc.perform(post("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isCreated());

        // Validate the ArticleMission in the database
        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeCreate + 1);
        ArticleMission testArticleMission = articleMissionList.get(articleMissionList.size() - 1);
        assertThat(testArticleMission.getQuantitePlanifiee()).isEqualTo(DEFAULT_QUANTITE_PLANIFIEE);
        assertThat(testArticleMission.getQuantiteUtilisee()).isEqualTo(DEFAULT_QUANTITE_UTILISEE);
    }

    @Test
    @Transactional
    public void createArticleMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleMissionRepository.findAll().size();

        // Create the ArticleMission with an existing ID
        articleMission.setId(1L);
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(articleMission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleMissionMockMvc.perform(post("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleMission in the database
        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQuantitePlanifieeIsRequired() throws Exception {
        int databaseSizeBeforeTest = articleMissionRepository.findAll().size();
        // set the field null
        articleMission.setQuantitePlanifiee(null);

        // Create the ArticleMission, which fails.
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(articleMission);


        restArticleMissionMockMvc.perform(post("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isBadRequest());

        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantiteUtiliseeIsRequired() throws Exception {
        int databaseSizeBeforeTest = articleMissionRepository.findAll().size();
        // set the field null
        articleMission.setQuantiteUtilisee(null);

        // Create the ArticleMission, which fails.
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(articleMission);


        restArticleMissionMockMvc.perform(post("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isBadRequest());

        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllArticleMissions() throws Exception {
        // Initialize the database
        articleMissionRepository.saveAndFlush(articleMission);

        // Get all the articleMissionList
        restArticleMissionMockMvc.perform(get("/api/article-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(articleMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantitePlanifiee").value(hasItem(DEFAULT_QUANTITE_PLANIFIEE)))
            .andExpect(jsonPath("$.[*].quantiteUtilisee").value(hasItem(DEFAULT_QUANTITE_UTILISEE)));
    }
    
    @Test
    @Transactional
    public void getArticleMission() throws Exception {
        // Initialize the database
        articleMissionRepository.saveAndFlush(articleMission);

        // Get the articleMission
        restArticleMissionMockMvc.perform(get("/api/article-missions/{id}", articleMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(articleMission.getId().intValue()))
            .andExpect(jsonPath("$.quantitePlanifiee").value(DEFAULT_QUANTITE_PLANIFIEE))
            .andExpect(jsonPath("$.quantiteUtilisee").value(DEFAULT_QUANTITE_UTILISEE));
    }
    @Test
    @Transactional
    public void getNonExistingArticleMission() throws Exception {
        // Get the articleMission
        restArticleMissionMockMvc.perform(get("/api/article-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleMission() throws Exception {
        // Initialize the database
        articleMissionRepository.saveAndFlush(articleMission);

        int databaseSizeBeforeUpdate = articleMissionRepository.findAll().size();

        // Update the articleMission
        ArticleMission updatedArticleMission = articleMissionRepository.findById(articleMission.getId()).get();
        // Disconnect from session so that the updates on updatedArticleMission are not directly saved in db
        em.detach(updatedArticleMission);
        updatedArticleMission
            .quantitePlanifiee(UPDATED_QUANTITE_PLANIFIEE)
            .quantiteUtilisee(UPDATED_QUANTITE_UTILISEE);
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(updatedArticleMission);

        restArticleMissionMockMvc.perform(put("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isOk());

        // Validate the ArticleMission in the database
        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeUpdate);
        ArticleMission testArticleMission = articleMissionList.get(articleMissionList.size() - 1);
        assertThat(testArticleMission.getQuantitePlanifiee()).isEqualTo(UPDATED_QUANTITE_PLANIFIEE);
        assertThat(testArticleMission.getQuantiteUtilisee()).isEqualTo(UPDATED_QUANTITE_UTILISEE);
    }

    @Test
    @Transactional
    public void updateNonExistingArticleMission() throws Exception {
        int databaseSizeBeforeUpdate = articleMissionRepository.findAll().size();

        // Create the ArticleMission
        ArticleMissionDTO articleMissionDTO = articleMissionMapper.toDto(articleMission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleMissionMockMvc.perform(put("/api/article-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleMission in the database
        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticleMission() throws Exception {
        // Initialize the database
        articleMissionRepository.saveAndFlush(articleMission);

        int databaseSizeBeforeDelete = articleMissionRepository.findAll().size();

        // Delete the articleMission
        restArticleMissionMockMvc.perform(delete("/api/article-missions/{id}", articleMission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArticleMission> articleMissionList = articleMissionRepository.findAll();
        assertThat(articleMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
