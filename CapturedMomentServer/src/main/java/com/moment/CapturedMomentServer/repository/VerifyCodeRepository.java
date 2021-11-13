package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 2021-11-08 김예진*/
@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCode, Long> {
    public VerifyCode findByEmail(String email);
    public boolean existsByEmail(String email);
    public void deleteByEmail(String email);
}
