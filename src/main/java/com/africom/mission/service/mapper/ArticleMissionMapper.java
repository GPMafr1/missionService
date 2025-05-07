package com.africom.mission.service.mapper;


import com.africom.mission.domain.*;
import com.africom.mission.service.dto.ArticleMissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArticleMission} and its DTO {@link ArticleMissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArticleMissionMapper extends EntityMapper<ArticleMissionDTO, ArticleMission> {



    default ArticleMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArticleMission articleMission = new ArticleMission();
        articleMission.setId(id);
        return articleMission;
    }
}
