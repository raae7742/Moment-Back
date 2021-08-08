package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/* User Entity에 대한 Repository (현애, 2021-08-01) */

public interface UserRepository extends JpaRepository<User, Long> {

    /* 유저의 email을 기준으로 JWT 토큰 생성 */
    Optional<User> findByEmail(String email);
}
