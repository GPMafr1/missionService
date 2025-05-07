package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.TacheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tache} and its DTO {@link TacheDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TacheMapper extends EntityMapper<TacheDTO, Tache> {



    default Tache fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tache tache = new Tache();
        tache.setId(id);
        return tache;
    }
}
