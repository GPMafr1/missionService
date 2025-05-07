package com.africom.mission.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class OrdreTravailClientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdreTravailClientDTO.class);
        OrdreTravailClientDTO ordreTravailClientDTO1 = new OrdreTravailClientDTO();
        ordreTravailClientDTO1.setId(1L);
        OrdreTravailClientDTO ordreTravailClientDTO2 = new OrdreTravailClientDTO();
        assertThat(ordreTravailClientDTO1).isNotEqualTo(ordreTravailClientDTO2);
        ordreTravailClientDTO2.setId(ordreTravailClientDTO1.getId());
        assertThat(ordreTravailClientDTO1).isEqualTo(ordreTravailClientDTO2);
        ordreTravailClientDTO2.setId(2L);
        assertThat(ordreTravailClientDTO1).isNotEqualTo(ordreTravailClientDTO2);
        ordreTravailClientDTO1.setId(null);
        assertThat(ordreTravailClientDTO1).isNotEqualTo(ordreTravailClientDTO2);
    }
}
