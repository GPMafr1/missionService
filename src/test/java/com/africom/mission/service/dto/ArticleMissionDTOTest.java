package com.africom.mission.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class ArticleMissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleMissionDTO.class);
        ArticleMissionDTO articleMissionDTO1 = new ArticleMissionDTO();
        articleMissionDTO1.setId(1L);
        ArticleMissionDTO articleMissionDTO2 = new ArticleMissionDTO();
        assertThat(articleMissionDTO1).isNotEqualTo(articleMissionDTO2);
        articleMissionDTO2.setId(articleMissionDTO1.getId());
        assertThat(articleMissionDTO1).isEqualTo(articleMissionDTO2);
        articleMissionDTO2.setId(2L);
        assertThat(articleMissionDTO1).isNotEqualTo(articleMissionDTO2);
        articleMissionDTO1.setId(null);
        assertThat(articleMissionDTO1).isNotEqualTo(articleMissionDTO2);
    }
}
