package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.PieceJointeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PieceJointe} and its DTO {@link PieceJointeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PieceJointeMapper extends EntityMapper<PieceJointeDTO, PieceJointe> {



    default PieceJointe fromId(Long id) {
        if (id == null) {
            return null;
        }
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setId(id);
        return pieceJointe;
    }
}
