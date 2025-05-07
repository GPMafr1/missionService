package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.ActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activite} and its DTO {@link ActiviteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActiviteMapper extends EntityMapper<ActiviteDTO, Activite> {



    default Activite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activite activite = new Activite();
        activite.setId(id);
        return activite;
    }
}
