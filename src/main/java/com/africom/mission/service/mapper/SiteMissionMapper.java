package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.SiteMissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SiteMission} and its DTO {@link SiteMissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SiteMissionMapper extends EntityMapper<SiteMissionDTO, SiteMission> {



    default SiteMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        SiteMission siteMission = new SiteMission();
        siteMission.setId(id);
        return siteMission;
    }
}
