package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.SSTDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SST} and its DTO {@link SSTDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SSTMapper extends EntityMapper<SSTDTO, SST> {



    default SST fromId(Long id) {
        if (id == null) {
            return null;
        }
        SST sST = new SST();
        sST.setId(id);
        return sST;
    }
}
