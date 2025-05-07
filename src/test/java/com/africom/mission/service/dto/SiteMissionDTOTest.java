package com.africom.mission.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class SiteMissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteMissionDTO.class);
        SiteMissionDTO siteMissionDTO1 = new SiteMissionDTO();
        siteMissionDTO1.setId(1L);
        SiteMissionDTO siteMissionDTO2 = new SiteMissionDTO();
        assertThat(siteMissionDTO1).isNotEqualTo(siteMissionDTO2);
        siteMissionDTO2.setId(siteMissionDTO1.getId());
        assertThat(siteMissionDTO1).isEqualTo(siteMissionDTO2);
        siteMissionDTO2.setId(2L);
        assertThat(siteMissionDTO1).isNotEqualTo(siteMissionDTO2);
        siteMissionDTO1.setId(null);
        assertThat(siteMissionDTO1).isNotEqualTo(siteMissionDTO2);
    }
}
