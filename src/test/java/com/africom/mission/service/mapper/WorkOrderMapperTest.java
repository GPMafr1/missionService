package com.africom.mission.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkOrderMapperTest {

    private WorkOrderMapper workOrderMapper;

    @BeforeEach
    public void setUp() {
        workOrderMapper = new WorkOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(workOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(workOrderMapper.fromId(null)).isNull();
    }
}
