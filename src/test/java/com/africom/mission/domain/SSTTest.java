package com.africom.mission.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.mission.web.rest.TestUtil;

public class SSTTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SST.class);
        SST sST1 = new SST();
        sST1.setId(1L);
        SST sST2 = new SST();
        sST2.setId(sST1.getId());
        assertThat(sST1).isEqualTo(sST2);
        sST2.setId(2L);
        assertThat(sST1).isNotEqualTo(sST2);
        sST1.setId(null);
        assertThat(sST1).isNotEqualTo(sST2);
    }
}
