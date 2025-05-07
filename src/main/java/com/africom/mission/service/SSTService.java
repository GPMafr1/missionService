package com.africom.mission.service;

import com.africom.mission.domain.SST;
import com.africom.mission.repository.SSTRepository;
import com.africom.mission.service.dto.SSTDTO;
import com.africom.mission.service.mapper.SSTMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SST}.
 */
@Service
@Transactional
public class SSTService {

    private final Logger log = LoggerFactory.getLogger(SSTService.class);

    private final SSTRepository sSTRepository;

    private final SSTMapper sSTMapper;

    public SSTService(SSTRepository sSTRepository, SSTMapper sSTMapper) {
        this.sSTRepository = sSTRepository;
        this.sSTMapper = sSTMapper;
    }

    /**
     * Save a sST.
     *
     * @param sSTDTO the entity to save.
     * @return the persisted entity.
     */
    public SSTDTO save(SSTDTO sSTDTO) {
        log.debug("Request to save SST : {}", sSTDTO);
        SST sST = sSTMapper.toEntity(sSTDTO);
        sST = sSTRepository.save(sST);
        return sSTMapper.toDto(sST);
    }

    /**
     * Get all the sSTS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SSTDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SSTS");
        return sSTRepository.findAll(pageable)
            .map(sSTMapper::toDto);
    }


    /**
     * Get one sST by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SSTDTO> findOne(Long id) {
        log.debug("Request to get SST : {}", id);
        return sSTRepository.findById(id)
            .map(sSTMapper::toDto);
    }

    /**
     * Delete the sST by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SST : {}", id);
        sSTRepository.deleteById(id);
    }
}
