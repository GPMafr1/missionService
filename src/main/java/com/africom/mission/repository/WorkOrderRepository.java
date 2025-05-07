package com.africom.mission.repository;

import com.africom.mission.domain.WorkOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WorkOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
}
