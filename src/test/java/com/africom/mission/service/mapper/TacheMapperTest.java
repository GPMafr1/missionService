package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TacheMapperTest {

    private TacheMapper tacheMapper;

    @BeforeEach
    public void setUp() {
        tacheMapper = new TacheMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tacheMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tacheMapper.fromId(null)).isNull();
    }
}
