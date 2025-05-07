package com.africom.mission.web.rest;

import com.africom.mission.MissionServiceApp;
import com.africom.mission.domain.WorkOrder;
import com.africom.mission.repository.WorkOrderRepository;
import com.africom.mission.service.WorkOrderService;
import com.africom.mission.service.dto.WorkOrderDTO;
import com.africom.mission.service.mapper.WorkOrderMapper;

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
import java.math.BigDecimal;
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
 * Integration tests for the {@link WorkOrderResource} REST controller.
 */
@SpringBootTest(classes = MissionServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WorkOrderResourceIT {

    private static final String DEFAULT_DEMANDEUR = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDEUR = "BBBBBBBBBB";

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_HEURE_DEBUT_PREVISIONNEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_DEBUT_PREVISIONNEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_HEURE_FIN_PREVISIONNEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_FIN_PREVISIONNEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_HEURE_DEBUT_REEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_DEBUT_REEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_HEURE_FIN_REEL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_HEURE_FIN_REEL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATERIEL_UTILISE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIEL_UTILISE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARQUE = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_FICHE_INTERVENTION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FICHE_INTERVENTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_HEBERGEMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEBERGEMENT = new BigDecimal(2);

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkOrderMockMvc;

    private WorkOrder workOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkOrder createEntity(EntityManager em) {
        WorkOrder workOrder = new WorkOrder()
            .demandeur(DEFAULT_DEMANDEUR)
            .motif(DEFAULT_MOTIF)
            .dateHeureDebutPrevisionnel(DEFAULT_DATE_HEURE_DEBUT_PREVISIONNEL)
            .dateHeureFinPrevisionnel(DEFAULT_DATE_HEURE_FIN_PREVISIONNEL)
            .dateHeureDebutReel(DEFAULT_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(DEFAULT_DATE_HEURE_FIN_REEL)
            .materielUtilise(DEFAULT_MATERIEL_UTILISE)
            .remarque(DEFAULT_REMARQUE)
            .numeroFicheIntervention(DEFAULT_NUMERO_FICHE_INTERVENTION)
            .hebergement(DEFAULT_HEBERGEMENT);
        return workOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkOrder createUpdatedEntity(EntityManager em) {
        WorkOrder workOrder = new WorkOrder()
            .demandeur(UPDATED_DEMANDEUR)
            .motif(UPDATED_MOTIF)
            .dateHeureDebutPrevisionnel(UPDATED_DATE_HEURE_DEBUT_PREVISIONNEL)
            .dateHeureFinPrevisionnel(UPDATED_DATE_HEURE_FIN_PREVISIONNEL)
            .dateHeureDebutReel(UPDATED_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(UPDATED_DATE_HEURE_FIN_REEL)
            .materielUtilise(UPDATED_MATERIEL_UTILISE)
            .remarque(UPDATED_REMARQUE)
            .numeroFicheIntervention(UPDATED_NUMERO_FICHE_INTERVENTION)
            .hebergement(UPDATED_HEBERGEMENT);
        return workOrder;
    }

    @BeforeEach
    public void initTest() {
        workOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkOrder() throws Exception {
        int databaseSizeBeforeCreate = workOrderRepository.findAll().size();
        // Create the WorkOrder
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);
        restWorkOrderMockMvc.perform(post("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the WorkOrder in the database
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeCreate + 1);
        WorkOrder testWorkOrder = workOrderList.get(workOrderList.size() - 1);
        assertThat(testWorkOrder.getDemandeur()).isEqualTo(DEFAULT_DEMANDEUR);
        assertThat(testWorkOrder.getMotif()).isEqualTo(DEFAULT_MOTIF);
        assertThat(testWorkOrder.getDateHeureDebutPrevisionnel()).isEqualTo(DEFAULT_DATE_HEURE_DEBUT_PREVISIONNEL);
        assertThat(testWorkOrder.getDateHeureFinPrevisionnel()).isEqualTo(DEFAULT_DATE_HEURE_FIN_PREVISIONNEL);
        assertThat(testWorkOrder.getDateHeureDebutReel()).isEqualTo(DEFAULT_DATE_HEURE_DEBUT_REEL);
        assertThat(testWorkOrder.getDateHeureFinReel()).isEqualTo(DEFAULT_DATE_HEURE_FIN_REEL);
        assertThat(testWorkOrder.getMaterielUtilise()).isEqualTo(DEFAULT_MATERIEL_UTILISE);
        assertThat(testWorkOrder.getRemarque()).isEqualTo(DEFAULT_REMARQUE);
        assertThat(testWorkOrder.getNumeroFicheIntervention()).isEqualTo(DEFAULT_NUMERO_FICHE_INTERVENTION);
        assertThat(testWorkOrder.getHebergement()).isEqualTo(DEFAULT_HEBERGEMENT);
    }

    @Test
    @Transactional
    public void createWorkOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workOrderRepository.findAll().size();

        // Create the WorkOrder with an existing ID
        workOrder.setId(1L);
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkOrderMockMvc.perform(post("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkOrder in the database
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMotifIsRequired() throws Exception {
        int databaseSizeBeforeTest = workOrderRepository.findAll().size();
        // set the field null
        workOrder.setMotif(null);

        // Create the WorkOrder, which fails.
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);


        restWorkOrderMockMvc.perform(post("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isBadRequest());

        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateHeureDebutPrevisionnelIsRequired() throws Exception {
        int databaseSizeBeforeTest = workOrderRepository.findAll().size();
        // set the field null
        workOrder.setDateHeureDebutPrevisionnel(null);

        // Create the WorkOrder, which fails.
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);


        restWorkOrderMockMvc.perform(post("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isBadRequest());

        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateHeureFinPrevisionnelIsRequired() throws Exception {
        int databaseSizeBeforeTest = workOrderRepository.findAll().size();
        // set the field null
        workOrder.setDateHeureFinPrevisionnel(null);

        // Create the WorkOrder, which fails.
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);


        restWorkOrderMockMvc.perform(post("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isBadRequest());

        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWorkOrders() throws Exception {
        // Initialize the database
        workOrderRepository.saveAndFlush(workOrder);

        // Get all the workOrderList
        restWorkOrderMockMvc.perform(get("/api/work-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].demandeur").value(hasItem(DEFAULT_DEMANDEUR)))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)))
            .andExpect(jsonPath("$.[*].dateHeureDebutPrevisionnel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_DEBUT_PREVISIONNEL))))
            .andExpect(jsonPath("$.[*].dateHeureFinPrevisionnel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_FIN_PREVISIONNEL))))
            .andExpect(jsonPath("$.[*].dateHeureDebutReel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_DEBUT_REEL))))
            .andExpect(jsonPath("$.[*].dateHeureFinReel").value(hasItem(sameInstant(DEFAULT_DATE_HEURE_FIN_REEL))))
            .andExpect(jsonPath("$.[*].materielUtilise").value(hasItem(DEFAULT_MATERIEL_UTILISE)))
            .andExpect(jsonPath("$.[*].remarque").value(hasItem(DEFAULT_REMARQUE)))
            .andExpect(jsonPath("$.[*].numeroFicheIntervention").value(hasItem(DEFAULT_NUMERO_FICHE_INTERVENTION)))
            .andExpect(jsonPath("$.[*].hebergement").value(hasItem(DEFAULT_HEBERGEMENT.intValue())));
    }
    
    @Test
    @Transactional
    public void getWorkOrder() throws Exception {
        // Initialize the database
        workOrderRepository.saveAndFlush(workOrder);

        // Get the workOrder
        restWorkOrderMockMvc.perform(get("/api/work-orders/{id}", workOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workOrder.getId().intValue()))
            .andExpect(jsonPath("$.demandeur").value(DEFAULT_DEMANDEUR))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF))
            .andExpect(jsonPath("$.dateHeureDebutPrevisionnel").value(sameInstant(DEFAULT_DATE_HEURE_DEBUT_PREVISIONNEL)))
            .andExpect(jsonPath("$.dateHeureFinPrevisionnel").value(sameInstant(DEFAULT_DATE_HEURE_FIN_PREVISIONNEL)))
            .andExpect(jsonPath("$.dateHeureDebutReel").value(sameInstant(DEFAULT_DATE_HEURE_DEBUT_REEL)))
            .andExpect(jsonPath("$.dateHeureFinReel").value(sameInstant(DEFAULT_DATE_HEURE_FIN_REEL)))
            .andExpect(jsonPath("$.materielUtilise").value(DEFAULT_MATERIEL_UTILISE))
            .andExpect(jsonPath("$.remarque").value(DEFAULT_REMARQUE))
            .andExpect(jsonPath("$.numeroFicheIntervention").value(DEFAULT_NUMERO_FICHE_INTERVENTION))
            .andExpect(jsonPath("$.hebergement").value(DEFAULT_HEBERGEMENT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingWorkOrder() throws Exception {
        // Get the workOrder
        restWorkOrderMockMvc.perform(get("/api/work-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkOrder() throws Exception {
        // Initialize the database
        workOrderRepository.saveAndFlush(workOrder);

        int databaseSizeBeforeUpdate = workOrderRepository.findAll().size();

        // Update the workOrder
        WorkOrder updatedWorkOrder = workOrderRepository.findById(workOrder.getId()).get();
        // Disconnect from session so that the updates on updatedWorkOrder are not directly saved in db
        em.detach(updatedWorkOrder);
        updatedWorkOrder
            .demandeur(UPDATED_DEMANDEUR)
            .motif(UPDATED_MOTIF)
            .dateHeureDebutPrevisionnel(UPDATED_DATE_HEURE_DEBUT_PREVISIONNEL)
            .dateHeureFinPrevisionnel(UPDATED_DATE_HEURE_FIN_PREVISIONNEL)
            .dateHeureDebutReel(UPDATED_DATE_HEURE_DEBUT_REEL)
            .dateHeureFinReel(UPDATED_DATE_HEURE_FIN_REEL)
            .materielUtilise(UPDATED_MATERIEL_UTILISE)
            .remarque(UPDATED_REMARQUE)
            .numeroFicheIntervention(UPDATED_NUMERO_FICHE_INTERVENTION)
            .hebergement(UPDATED_HEBERGEMENT);
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(updatedWorkOrder);

        restWorkOrderMockMvc.perform(put("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isOk());

        // Validate the WorkOrder in the database
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeUpdate);
        WorkOrder testWorkOrder = workOrderList.get(workOrderList.size() - 1);
        assertThat(testWorkOrder.getDemandeur()).isEqualTo(UPDATED_DEMANDEUR);
        assertThat(testWorkOrder.getMotif()).isEqualTo(UPDATED_MOTIF);
        assertThat(testWorkOrder.getDateHeureDebutPrevisionnel()).isEqualTo(UPDATED_DATE_HEURE_DEBUT_PREVISIONNEL);
        assertThat(testWorkOrder.getDateHeureFinPrevisionnel()).isEqualTo(UPDATED_DATE_HEURE_FIN_PREVISIONNEL);
        assertThat(testWorkOrder.getDateHeureDebutReel()).isEqualTo(UPDATED_DATE_HEURE_DEBUT_REEL);
        assertThat(testWorkOrder.getDateHeureFinReel()).isEqualTo(UPDATED_DATE_HEURE_FIN_REEL);
        assertThat(testWorkOrder.getMaterielUtilise()).isEqualTo(UPDATED_MATERIEL_UTILISE);
        assertThat(testWorkOrder.getRemarque()).isEqualTo(UPDATED_REMARQUE);
        assertThat(testWorkOrder.getNumeroFicheIntervention()).isEqualTo(UPDATED_NUMERO_FICHE_INTERVENTION);
        assertThat(testWorkOrder.getHebergement()).isEqualTo(UPDATED_HEBERGEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkOrder() throws Exception {
        int databaseSizeBeforeUpdate = workOrderRepository.findAll().size();

        // Create the WorkOrder
        WorkOrderDTO workOrderDTO = workOrderMapper.toDto(workOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkOrderMockMvc.perform(put("/api/work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkOrder in the database
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWorkOrder() throws Exception {
        // Initialize the database
        workOrderRepository.saveAndFlush(workOrder);

        int databaseSizeBeforeDelete = workOrderRepository.findAll().size();

        // Delete the workOrder
        restWorkOrderMockMvc.perform(delete("/api/work-orders/{id}", workOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        assertThat(workOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
