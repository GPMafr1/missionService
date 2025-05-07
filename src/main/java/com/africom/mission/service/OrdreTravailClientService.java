package com.africom.mission.service;

import com.africom.mission.domain.OrdreTravailClient;
import com.africom.mission.repository.OrdreTravailClientRepository;
import com.africom.mission.service.dto.OrdreTravailClientDTO;
import com.africom.mission.service.mapper.OrdreTravailClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrdreTravailClient}.
 */
@Service
@Transactional
public class OrdreTravailClientService {

    private final Logger log = LoggerFactory.getLogger(OrdreTravailClientService.class);

    private final OrdreTravailClientRepository ordreTravailClientRepository;

    private final OrdreTravailClientMapper ordreTravailClientMapper;

    public OrdreTravailClientService(OrdreTravailClientRepository ordreTravailClientRepository, OrdreTravailClientMapper ordreTravailClientMapper) {
        this.ordreTravailClientRepository = ordreTravailClientRepository;
        this.ordreTravailClientMapper = ordreTravailClientMapper;
    }

    /**
     * Save a ordreTravailClient.
     *
     * @param ordreTravailClientDTO the entity to save.
     * @return the persisted entity.
     */
    public OrdreTravailClientDTO save(OrdreTravailClientDTO ordreTravailClientDTO) {
        log.debug("Request to save OrdreTravailClient : {}", ordreTravailClientDTO);
        OrdreTravailClient ordreTravailClient = ordreTravailClientMapper.toEntity(ordreTravailClientDTO);
        ordreTravailClient = ordreTravailClientRepository.save(ordreTravailClient);
        return ordreTravailClientMapper.toDto(ordreTravailClient);
    }

    /**
     * Get all the ordreTravailClients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrdreTravailClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrdreTravailClients");
        return ordreTravailClientRepository.findAll(pageable)
            .map(ordreTravailClientMapper::toDto);
    }


    /**
     * Get one ordreTravailClient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrdreTravailClientDTO> findOne(Long id) {
        log.debug("Request to get OrdreTravailClient : {}", id);
        return ordreTravailClientRepository.findById(id)
            .map(ordreTravailClientMapper::toDto);
    }

    /**
     * Delete the ordreTravailClient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrdreTravailClient : {}", id);
        ordreTravailClientRepository.deleteById(id);
    }
}
