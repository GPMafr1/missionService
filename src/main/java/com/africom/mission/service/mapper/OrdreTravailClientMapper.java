package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.OrdreTravailClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrdreTravailClient} and its DTO {@link OrdreTravailClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrdreTravailClientMapper extends EntityMapper<OrdreTravailClientDTO, OrdreTravailClient> {



    default OrdreTravailClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrdreTravailClient ordreTravailClient = new OrdreTravailClient();
        ordreTravailClient.setId(id);
        return ordreTravailClient;
    }
}
