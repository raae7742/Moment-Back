package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.RandomIDTest;
import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdTestRepository extends JpaRepository<RandomIDTest, Long> {
}
