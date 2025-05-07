package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.WorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkOrder} and its DTO {@link WorkOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkOrderMapper extends EntityMapper<WorkOrderDTO, WorkOrder> {



    default WorkOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(id);
        return workOrder;
    }
}
