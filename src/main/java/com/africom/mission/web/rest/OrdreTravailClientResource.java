package com.africom.mission.web.rest;

import com.africom.mission.service.OrdreTravailClientService;
import com.africom.mission.web.rest.errors.BadRequestAlertException;
import com.africom.mission.service.dto.OrdreTravailClientDTO;

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
 * REST controller for managing {@link com.africom.mission.domain.OrdreTravailClient}.
 */
@RestController
@RequestMapping("/api")
public class OrdreTravailClientResource {

    private final Logger log = LoggerFactory.getLogger(OrdreTravailClientResource.class);

    private static final String ENTITY_NAME = "missionServiceOrdreTravailClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdreTravailClientService ordreTravailClientService;

    public OrdreTravailClientResource(OrdreTravailClientService ordreTravailClientService) {
        this.ordreTravailClientService = ordreTravailClientService;
    }

    /**
     * {@code POST  /ordre-travail-clients} : Create a new ordreTravailClient.
     *
     * @param ordreTravailClientDTO the ordreTravailClientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordreTravailClientDTO, or with status {@code 400 (Bad Request)} if the ordreTravailClient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordre-travail-clients")
    public ResponseEntity<OrdreTravailClientDTO> createOrdreTravailClient(@Valid @RequestBody OrdreTravailClientDTO ordreTravailClientDTO) throws URISyntaxException {
        log.debug("REST request to save OrdreTravailClient : {}", ordreTravailClientDTO);
        if (ordreTravailClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordreTravailClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdreTravailClientDTO result = ordreTravailClientService.save(ordreTravailClientDTO);
        return ResponseEntity.created(new URI("/api/ordre-travail-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ordre-travail-clients} : Updates an existing ordreTravailClient.
     *
     * @param ordreTravailClientDTO the ordreTravailClientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordreTravailClientDTO,
     * or with status {@code 400 (Bad Request)} if the ordreTravailClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordreTravailClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordre-travail-clients")
    public ResponseEntity<OrdreTravailClientDTO> updateOrdreTravailClient(@Valid @RequestBody OrdreTravailClientDTO ordreTravailClientDTO) throws URISyntaxException {
        log.debug("REST request to update OrdreTravailClient : {}", ordreTravailClientDTO);
        if (ordreTravailClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdreTravailClientDTO result = ordreTravailClientService.save(ordreTravailClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordreTravailClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ordre-travail-clients} : get all the ordreTravailClients.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordreTravailClients in body.
     */
    @GetMapping("/ordre-travail-clients")
    public ResponseEntity<List<OrdreTravailClientDTO>> getAllOrdreTravailClients(Pageable pageable) {
        log.debug("REST request to get a page of OrdreTravailClients");
        Page<OrdreTravailClientDTO> page = ordreTravailClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ordre-travail-clients/:id} : get the "id" ordreTravailClient.
     *
     * @param id the id of the ordreTravailClientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordreTravailClientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordre-travail-clients/{id}")
    public ResponseEntity<OrdreTravailClientDTO> getOrdreTravailClient(@PathVariable Long id) {
        log.debug("REST request to get OrdreTravailClient : {}", id);
        Optional<OrdreTravailClientDTO> ordreTravailClientDTO = ordreTravailClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordreTravailClientDTO);
    }

    /**
     * {@code DELETE  /ordre-travail-clients/:id} : delete the "id" ordreTravailClient.
     *
     * @param id the id of the ordreTravailClientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordre-travail-clients/{id}")
    public ResponseEntity<Void> deleteOrdreTravailClient(@PathVariable Long id) {
        log.debug("REST request to delete OrdreTravailClient : {}", id);
        ordreTravailClientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
