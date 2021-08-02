package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/* User Entity에 대한 Repository (현애, 2021-08-01) */

public interface UserRepository extends JpaRepository<User, Long> {
}
