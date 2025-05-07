package com.africom.mission.service;

import com.africom.mission.domain.ArticleMission;
import com.africom.mission.repository.ArticleMissionRepository;
import com.africom.mission.service.dto.ArticleMissionDTO;
import com.africom.mission.service.mapper.ArticleMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ArticleMission}.
 */
@Service
@Transactional
public class ArticleMissionService {

    private final Logger log = LoggerFactory.getLogger(ArticleMissionService.class);

    private final ArticleMissionRepository articleMissionRepository;

    private final ArticleMissionMapper articleMissionMapper;

    public ArticleMissionService(ArticleMissionRepository articleMissionRepository, ArticleMissionMapper articleMissionMapper) {
        this.articleMissionRepository = articleMissionRepository;
        this.articleMissionMapper = articleMissionMapper;
    }

    /**
     * Save a articleMission.
     *
     * @param articleMissionDTO the entity to save.
     * @return the persisted entity.
     */
    public ArticleMissionDTO save(ArticleMissionDTO articleMissionDTO) {
        log.debug("Request to save ArticleMission : {}", articleMissionDTO);
        ArticleMission articleMission = articleMissionMapper.toEntity(articleMissionDTO);
        articleMission = articleMissionRepository.save(articleMission);
        return articleMissionMapper.toDto(articleMission);
    }

    /**
     * Get all the articleMissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ArticleMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ArticleMissions");
        return articleMissionRepository.findAll(pageable)
            .map(articleMissionMapper::toDto);
    }


    /**
     * Get one articleMission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ArticleMissionDTO> findOne(Long id) {
        log.debug("Request to get ArticleMission : {}", id);
        return articleMissionRepository.findById(id)
            .map(articleMissionMapper::toDto);
    }

    /**
     * Delete the articleMission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ArticleMission : {}", id);
        articleMissionRepository.deleteById(id);
    }
}
