package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PieceJointeMapperTest {

    private PieceJointeMapper pieceJointeMapper;

    @BeforeEach
    public void setUp() {
        pieceJointeMapper = new PieceJointeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pieceJointeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pieceJointeMapper.fromId(null)).isNull();
    }
}
