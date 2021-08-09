package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* User Entity에 대한 Repository (현애, 2021-08-01 예진, 2021-08-08) */
@SuppressWarnings("unchecked")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //email로 회원 조회
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    //모든 회원 조회
    List<User> findAll();
}
