package com.africom.mission.web.rest;

import com.africom.mission.service.SiteMissionService;
import com.africom.mission.web.rest.errors.BadRequestAlertException;
import com.africom.mission.service.dto.SiteMissionDTO;

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
 * REST controller for managing {@link com.africom.mission.domain.SiteMission}.
 */
@RestController
@RequestMapping("/api")
public class SiteMissionResource {

    private final Logger log = LoggerFactory.getLogger(SiteMissionResource.class);

    private static final String ENTITY_NAME = "missionServiceSiteMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SiteMissionService siteMissionService;

    public SiteMissionResource(SiteMissionService siteMissionService) {
        this.siteMissionService = siteMissionService;
    }

    /**
     * {@code POST  /site-missions} : Create a new siteMission.
     *
     * @param siteMissionDTO the siteMissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new siteMissionDTO, or with status {@code 400 (Bad Request)} if the siteMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/site-missions")
    public ResponseEntity<SiteMissionDTO> createSiteMission(@Valid @RequestBody SiteMissionDTO siteMissionDTO) throws URISyntaxException {
        log.debug("REST request to save SiteMission : {}", siteMissionDTO);
        if (siteMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new siteMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SiteMissionDTO result = siteMissionService.save(siteMissionDTO);
        return ResponseEntity.created(new URI("/api/site-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /site-missions} : Updates an existing siteMission.
     *
     * @param siteMissionDTO the siteMissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated siteMissionDTO,
     * or with status {@code 400 (Bad Request)} if the siteMissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the siteMissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/site-missions")
    public ResponseEntity<SiteMissionDTO> updateSiteMission(@Valid @RequestBody SiteMissionDTO siteMissionDTO) throws URISyntaxException {
        log.debug("REST request to update SiteMission : {}", siteMissionDTO);
        if (siteMissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiteMissionDTO result = siteMissionService.save(siteMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, siteMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /site-missions} : get all the siteMissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of siteMissions in body.
     */
    @GetMapping("/site-missions")
    public ResponseEntity<List<SiteMissionDTO>> getAllSiteMissions(Pageable pageable) {
        log.debug("REST request to get a page of SiteMissions");
        Page<SiteMissionDTO> page = siteMissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /site-missions/:id} : get the "id" siteMission.
     *
     * @param id the id of the siteMissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the siteMissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/site-missions/{id}")
    public ResponseEntity<SiteMissionDTO> getSiteMission(@PathVariable Long id) {
        log.debug("REST request to get SiteMission : {}", id);
        Optional<SiteMissionDTO> siteMissionDTO = siteMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siteMissionDTO);
    }

    /**
     * {@code DELETE  /site-missions/:id} : delete the "id" siteMission.
     *
     * @param id the id of the siteMissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/site-missions/{id}")
    public ResponseEntity<Void> deleteSiteMission(@PathVariable Long id) {
        log.debug("REST request to delete SiteMission : {}", id);
        siteMissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
