package com.africom.mission.service;

import com.africom.mission.domain.SiteMission;
import com.africom.mission.repository.SiteMissionRepository;
import com.africom.mission.service.dto.SiteMissionDTO;
import com.africom.mission.service.mapper.SiteMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SiteMission}.
 */
@Service
@Transactional
public class SiteMissionService {

    private final Logger log = LoggerFactory.getLogger(SiteMissionService.class);

    private final SiteMissionRepository siteMissionRepository;

    private final SiteMissionMapper siteMissionMapper;

    public SiteMissionService(SiteMissionRepository siteMissionRepository, SiteMissionMapper siteMissionMapper) {
        this.siteMissionRepository = siteMissionRepository;
        this.siteMissionMapper = siteMissionMapper;
    }

    /**
     * Save a siteMission.
     *
     * @param siteMissionDTO the entity to save.
     * @return the persisted entity.
     */
    public SiteMissionDTO save(SiteMissionDTO siteMissionDTO) {
        log.debug("Request to save SiteMission : {}", siteMissionDTO);
        SiteMission siteMission = siteMissionMapper.toEntity(siteMissionDTO);
        siteMission = siteMissionRepository.save(siteMission);
        return siteMissionMapper.toDto(siteMission);
    }

    /**
     * Get all the siteMissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SiteMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SiteMissions");
        return siteMissionRepository.findAll(pageable)
            .map(siteMissionMapper::toDto);
    }


    /**
     * Get one siteMission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SiteMissionDTO> findOne(Long id) {
        log.debug("Request to get SiteMission : {}", id);
        return siteMissionRepository.findById(id)
            .map(siteMissionMapper::toDto);
    }

    /**
     * Delete the siteMission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SiteMission : {}", id);
        siteMissionRepository.deleteById(id);
    }
}
