package com.africom.mission.repository;

import com.africom.mission.domain.OrdreTravailClient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrdreTravailClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdreTravailClientRepository extends JpaRepository<OrdreTravailClient, Long> {
}
