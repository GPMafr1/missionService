package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.OrdreTravailClient;
import com.africom.mission.repository.OrdreTravailClientRepository;
import com.africom.mission.service.OrdreTravailClientService;
import com.africom.mission.service.dto.OrdreTravailClientDTO;
import com.africom.mission.service.mapper.OrdreTravailClientMapper;

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
 * Integration tests for the {@link OrdreTravailClientResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrdreTravailClientResourceIT {

    private static final String DEFAULT_DEMANDEUR = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDEUR = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINE = "BBBBBBBBBB";

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    @Autowired
    private OrdreTravailClientRepository ordreTravailClientRepository;

    @Autowired
    private OrdreTravailClientMapper ordreTravailClientMapper;

    @Autowired
    private OrdreTravailClientService ordreTravailClientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrdreTravailClientMockMvc;

    private OrdreTravailClient ordreTravailClient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdreTravailClient createEntity(EntityManager em) {
        OrdreTravailClient ordreTravailClient = new OrdreTravailClient()
            .demandeur(DEFAULT_DEMANDEUR)
            .origine(DEFAULT_ORIGINE)
            .motif(DEFAULT_MOTIF);
        return ordreTravailClient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdreTravailClient createUpdatedEntity(EntityManager em) {
        OrdreTravailClient ordreTravailClient = new OrdreTravailClient()
            .demandeur(UPDATED_DEMANDEUR)
            .origine(UPDATED_ORIGINE)
            .motif(UPDATED_MOTIF);
        return ordreTravailClient;
    }

    @BeforeEach
    public void initTest() {
        ordreTravailClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdreTravailClient() throws Exception {
        int databaseSizeBeforeCreate = ordreTravailClientRepository.findAll().size();
        // Create the OrdreTravailClient
        OrdreTravailClientDTO ordreTravailClientDTO = ordreTravailClientMapper.toDto(ordreTravailClient);
        restOrdreTravailClientMockMvc.perform(post("/api/ordre-travail-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreTravailClientDTO)))
            .andExpect(status().isCreated());

        // Validate the OrdreTravailClient in the database
        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeCreate + 1);
        OrdreTravailClient testOrdreTravailClient = ordreTravailClientList.get(ordreTravailClientList.size() - 1);
        assertThat(testOrdreTravailClient.getDemandeur()).isEqualTo(DEFAULT_DEMANDEUR);
        assertThat(testOrdreTravailClient.getOrigine()).isEqualTo(DEFAULT_ORIGINE);
        assertThat(testOrdreTravailClient.getMotif()).isEqualTo(DEFAULT_MOTIF);
    }

    @Test
    @Transactional
    public void createOrdreTravailClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordreTravailClientRepository.findAll().size();

        // Create the OrdreTravailClient with an existing ID
        ordreTravailClient.setId(1L);
        OrdreTravailClientDTO ordreTravailClientDTO = ordreTravailClientMapper.toDto(ordreTravailClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdreTravailClientMockMvc.perform(post("/api/ordre-travail-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreTravailClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdreTravailClient in the database
        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMotifIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreTravailClientRepository.findAll().size();
        // set the field null
        ordreTravailClient.setMotif(null);

        // Create the OrdreTravailClient, which fails.
        OrdreTravailClientDTO ordreTravailClientDTO = ordreTravailClientMapper.toDto(ordreTravailClient);


        restOrdreTravailClientMockMvc.perform(post("/api/ordre-travail-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreTravailClientDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdreTravailClients() throws Exception {
        // Initialize the database
        ordreTravailClientRepository.saveAndFlush(ordreTravailClient);

        // Get all the ordreTravailClientList
        restOrdreTravailClientMockMvc.perform(get("/api/ordre-travail-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordreTravailClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].demandeur").value(hasItem(DEFAULT_DEMANDEUR)))
            .andExpect(jsonPath("$.[*].origine").value(hasItem(DEFAULT_ORIGINE)))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)));
    }
    
    @Test
    @Transactional
    public void getOrdreTravailClient() throws Exception {
        // Initialize the database
        ordreTravailClientRepository.saveAndFlush(ordreTravailClient);

        // Get the ordreTravailClient
        restOrdreTravailClientMockMvc.perform(get("/api/ordre-travail-clients/{id}", ordreTravailClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordreTravailClient.getId().intValue()))
            .andExpect(jsonPath("$.demandeur").value(DEFAULT_DEMANDEUR))
            .andExpect(jsonPath("$.origine").value(DEFAULT_ORIGINE))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF));
    }
    @Test
    @Transactional
    public void getNonExistingOrdreTravailClient() throws Exception {
        // Get the ordreTravailClient
        restOrdreTravailClientMockMvc.perform(get("/api/ordre-travail-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdreTravailClient() throws Exception {
        // Initialize the database
        ordreTravailClientRepository.saveAndFlush(ordreTravailClient);

        int databaseSizeBeforeUpdate = ordreTravailClientRepository.findAll().size();

        // Update the ordreTravailClient
        OrdreTravailClient updatedOrdreTravailClient = ordreTravailClientRepository.findById(ordreTravailClient.getId()).get();
        // Disconnect from session so that the updates on updatedOrdreTravailClient are not directly saved in db
        em.detach(updatedOrdreTravailClient);
        updatedOrdreTravailClient
            .demandeur(UPDATED_DEMANDEUR)
            .origine(UPDATED_ORIGINE)
            .motif(UPDATED_MOTIF);
        OrdreTravailClientDTO ordreTravailClientDTO = ordreTravailClientMapper.toDto(updatedOrdreTravailClient);

        restOrdreTravailClientMockMvc.perform(put("/api/ordre-travail-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreTravailClientDTO)))
            .andExpect(status().isOk());

        // Validate the OrdreTravailClient in the database
        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeUpdate);
        OrdreTravailClient testOrdreTravailClient = ordreTravailClientList.get(ordreTravailClientList.size() - 1);
        assertThat(testOrdreTravailClient.getDemandeur()).isEqualTo(UPDATED_DEMANDEUR);
        assertThat(testOrdreTravailClient.getOrigine()).isEqualTo(UPDATED_ORIGINE);
        assertThat(testOrdreTravailClient.getMotif()).isEqualTo(UPDATED_MOTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdreTravailClient() throws Exception {
        int databaseSizeBeforeUpdate = ordreTravailClientRepository.findAll().size();

        // Create the OrdreTravailClient
        OrdreTravailClientDTO ordreTravailClientDTO = ordreTravailClientMapper.toDto(ordreTravailClient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdreTravailClientMockMvc.perform(put("/api/ordre-travail-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreTravailClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdreTravailClient in the database
        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdreTravailClient() throws Exception {
        // Initialize the database
        ordreTravailClientRepository.saveAndFlush(ordreTravailClient);

        int databaseSizeBeforeDelete = ordreTravailClientRepository.findAll().size();

        // Delete the ordreTravailClient
        restOrdreTravailClientMockMvc.perform(delete("/api/ordre-travail-clients/{id}", ordreTravailClient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrdreTravailClient> ordreTravailClientList = ordreTravailClientRepository.findAll();
        assertThat(ordreTravailClientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
