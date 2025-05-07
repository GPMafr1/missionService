package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SSTMapperTest {

    private SSTMapper sSTMapper;

    @BeforeEach
    public void setUp() {
        sSTMapper = new SSTMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sSTMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sSTMapper.fromId(null)).isNull();
    }
}
