package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SiteMissionMapperTest {

    private SiteMissionMapper siteMissionMapper;

    @BeforeEach
    public void setUp() {
        siteMissionMapper = new SiteMissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(siteMissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(siteMissionMapper.fromId(null)).isNull();
    }
}
