package com.africom.mission.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class ArticleMissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleMission.class);
        ArticleMission articleMission1 = new ArticleMission();
        articleMission1.setId(1L);
        ArticleMission articleMission2 = new ArticleMission();
        articleMission2.setId(articleMission1.getId());
        assertThat(articleMission1).isEqualTo(articleMission2);
        articleMission2.setId(2L);
        assertThat(articleMission1).isNotEqualTo(articleMission2);
        articleMission1.setId(null);
        assertThat(articleMission1).isNotEqualTo(articleMission2);
    }
}
