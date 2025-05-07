package com.africom.mission.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class OrdreTravailClientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdreTravailClient.class);
        OrdreTravailClient ordreTravailClient1 = new OrdreTravailClient();
        ordreTravailClient1.setId(1L);
        OrdreTravailClient ordreTravailClient2 = new OrdreTravailClient();
        ordreTravailClient2.setId(ordreTravailClient1.getId());
        assertThat(ordreTravailClient1).isEqualTo(ordreTravailClient2);
        ordreTravailClient2.setId(2L);
        assertThat(ordreTravailClient1).isNotEqualTo(ordreTravailClient2);
        ordreTravailClient1.setId(null);
        assertThat(ordreTravailClient1).isNotEqualTo(ordreTravailClient2);
    }
}
