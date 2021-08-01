package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
