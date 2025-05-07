package com.africom.mission.web.rest;

import com.africom.mission.service.WorkOrderService;
import com.africom.mission.web.rest.errors.BadRequestAlertException;
import com.africom.mission.service.dto.WorkOrderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.africom.mission.domain.WorkOrder}.
 */
@RestController
@RequestMapping("/api")
public class WorkOrderResource {

    private final Logger log = LoggerFactory.getLogger(WorkOrderResource.class);

    private static final String ENTITY_NAME = "missionServiceWorkOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkOrderService workOrderService;

    public WorkOrderResource(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    /**
     * {@code POST  /work-orders} : Create a new workOrder.
     *
     * @param workOrderDTO the workOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workOrderDTO, or with status {@code 400 (Bad Request)} if the workOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-orders")
    public ResponseEntity<WorkOrderDTO> createWorkOrder(@Valid @RequestBody WorkOrderDTO workOrderDTO) throws URISyntaxException {
        log.debug("REST request to save WorkOrder : {}", workOrderDTO);
        if (workOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new workOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkOrderDTO result = workOrderService.save(workOrderDTO);
        return ResponseEntity.created(new URI("/api/work-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-orders} : Updates an existing workOrder.
     *
     * @param workOrderDTO the workOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workOrderDTO,
     * or with status {@code 400 (Bad Request)} if the workOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-orders")
    public ResponseEntity<WorkOrderDTO> updateWorkOrder(@Valid @RequestBody WorkOrderDTO workOrderDTO) throws URISyntaxException {
        log.debug("REST request to update WorkOrder : {}", workOrderDTO);
        if (workOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkOrderDTO result = workOrderService.save(workOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /work-orders} : get all the workOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workOrders in body.
     */
    @GetMapping("/work-orders")
    public ResponseEntity<List<WorkOrderDTO>> getAllWorkOrders(Pageable pageable) {
        log.debug("REST request to get a page of WorkOrders");
        Page<WorkOrderDTO> page = workOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-orders/:id} : get the "id" workOrder.
     *
     * @param id the id of the workOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-orders/{id}")
    public ResponseEntity<WorkOrderDTO> getWorkOrder(@PathVariable Long id) {
        log.debug("REST request to get WorkOrder : {}", id);
        Optional<WorkOrderDTO> workOrderDTO = workOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workOrderDTO);
    }

    /**
     * {@code DELETE  /work-orders/:id} : delete the "id" workOrder.
     *
     * @param id the id of the workOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-orders/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable Long id) {
        log.debug("REST request to delete WorkOrder : {}", id);
        workOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
