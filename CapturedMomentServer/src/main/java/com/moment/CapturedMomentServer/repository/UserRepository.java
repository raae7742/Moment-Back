package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* User Entity에 대한 Repository (현애, 2021-08-15 수정) */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);        // email 중복 여부

    Optional<User> findByEmail(String email);   // email로 회원 조회

    Optional<User> findById(Long id);

    List<User> findAll();                       //모든 회원 조회
}
