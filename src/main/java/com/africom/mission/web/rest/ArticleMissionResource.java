package com.africom.mission.web.rest;

import com.africom.mission.service.ArticleMissionService;
import com.africom.mission.web.rest.errors.BadRequestAlertException;
import com.africom.mission.service.dto.ArticleMissionDTO;

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
 * REST controller for managing {@link com.africom.mission.domain.ArticleMission}.
 */
@RestController
@RequestMapping("/api")
public class ArticleMissionResource {

    private final Logger log = LoggerFactory.getLogger(ArticleMissionResource.class);

    private static final String ENTITY_NAME = "missionServiceArticleMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArticleMissionService articleMissionService;

    public ArticleMissionResource(ArticleMissionService articleMissionService) {
        this.articleMissionService = articleMissionService;
    }

    /**
     * {@code POST  /article-missions} : Create a new articleMission.
     *
     * @param articleMissionDTO the articleMissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new articleMissionDTO, or with status {@code 400 (Bad Request)} if the articleMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/article-missions")
    public ResponseEntity<ArticleMissionDTO> createArticleMission(@Valid @RequestBody ArticleMissionDTO articleMissionDTO) throws URISyntaxException {
        log.debug("REST request to save ArticleMission : {}", articleMissionDTO);
        if (articleMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new articleMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleMissionDTO result = articleMissionService.save(articleMissionDTO);
        return ResponseEntity.created(new URI("/api/article-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /article-missions} : Updates an existing articleMission.
     *
     * @param articleMissionDTO the articleMissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated articleMissionDTO,
     * or with status {@code 400 (Bad Request)} if the articleMissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the articleMissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/article-missions")
    public ResponseEntity<ArticleMissionDTO> updateArticleMission(@Valid @RequestBody ArticleMissionDTO articleMissionDTO) throws URISyntaxException {
        log.debug("REST request to update ArticleMission : {}", articleMissionDTO);
        if (articleMissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArticleMissionDTO result = articleMissionService.save(articleMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, articleMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /article-missions} : get all the articleMissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articleMissions in body.
     */
    @GetMapping("/article-missions")
    public ResponseEntity<List<ArticleMissionDTO>> getAllArticleMissions(Pageable pageable) {
        log.debug("REST request to get a page of ArticleMissions");
        Page<ArticleMissionDTO> page = articleMissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /article-missions/:id} : get the "id" articleMission.
     *
     * @param id the id of the articleMissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the articleMissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-missions/{id}")
    public ResponseEntity<ArticleMissionDTO> getArticleMission(@PathVariable Long id) {
        log.debug("REST request to get ArticleMission : {}", id);
        Optional<ArticleMissionDTO> articleMissionDTO = articleMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(articleMissionDTO);
    }

    /**
     * {@code DELETE  /article-missions/:id} : delete the "id" articleMission.
     *
     * @param id the id of the articleMissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/article-missions/{id}")
    public ResponseEntity<Void> deleteArticleMission(@PathVariable Long id) {
        log.debug("REST request to delete ArticleMission : {}", id);
        articleMissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
