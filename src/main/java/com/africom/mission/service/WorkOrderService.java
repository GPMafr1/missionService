package com.africom.mission.service;

import com.africom.mission.domain.WorkOrder;
import com.africom.mission.repository.WorkOrderRepository;
import com.africom.mission.service.dto.WorkOrderDTO;
import com.africom.mission.service.mapper.WorkOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkOrder}.
 */
@Service
@Transactional
public class WorkOrderService {

    private final Logger log = LoggerFactory.getLogger(WorkOrderService.class);

    private final WorkOrderRepository workOrderRepository;

    private final WorkOrderMapper workOrderMapper;

    public WorkOrderService(WorkOrderRepository workOrderRepository, WorkOrderMapper workOrderMapper) {
        this.workOrderRepository = workOrderRepository;
        this.workOrderMapper = workOrderMapper;
    }

    /**
     * Save a workOrder.
     *
     * @param workOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkOrderDTO save(WorkOrderDTO workOrderDTO) {
        log.debug("Request to save WorkOrder : {}", workOrderDTO);
        WorkOrder workOrder = workOrderMapper.toEntity(workOrderDTO);
        workOrder = workOrderRepository.save(workOrder);
        return workOrderMapper.toDto(workOrder);
    }

    /**
     * Get all the workOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkOrders");
        return workOrderRepository.findAll(pageable)
            .map(workOrderMapper::toDto);
    }


    /**
     * Get one workOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkOrderDTO> findOne(Long id) {
        log.debug("Request to get WorkOrder : {}", id);
        return workOrderRepository.findById(id)
            .map(workOrderMapper::toDto);
    }

    /**
     * Delete the workOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkOrder : {}", id);
        workOrderRepository.deleteById(id);
    }
}
