package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdreTravailClientMapperTest {

    private OrdreTravailClientMapper ordreTravailClientMapper;

    @BeforeEach
    public void setUp() {
        ordreTravailClientMapper = new OrdreTravailClientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ordreTravailClientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ordreTravailClientMapper.fromId(null)).isNull();
    }
}
