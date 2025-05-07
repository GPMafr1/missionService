package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleMissionMapperTest {

    private ArticleMissionMapper articleMissionMapper;

    @BeforeEach
    public void setUp() {
        articleMissionMapper = new ArticleMissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(articleMissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(articleMissionMapper.fromId(null)).isNull();
    }
}
