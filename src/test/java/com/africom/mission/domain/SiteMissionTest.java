package com.africom.mission.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class SiteMissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteMission.class);
        SiteMission siteMission1 = new SiteMission();
        siteMission1.setId(1L);
        SiteMission siteMission2 = new SiteMission();
        siteMission2.setId(siteMission1.getId());
        assertThat(siteMission1).isEqualTo(siteMission2);
        siteMission2.setId(2L);
        assertThat(siteMission1).isNotEqualTo(siteMission2);
        siteMission1.setId(null);
        assertThat(siteMission1).isNotEqualTo(siteMission2);
    }
}
