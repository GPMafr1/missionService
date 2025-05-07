package com.africom.mission.repository;

import com.africom.mission.domain.SST;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SST entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SSTRepository extends JpaRepository<SST, Long> {
}
