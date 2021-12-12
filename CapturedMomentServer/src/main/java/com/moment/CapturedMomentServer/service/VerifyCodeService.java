package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.VerifyCode;
import com.moment.CapturedMomentServer.domain.VerifyCodeDto;
import com.moment.CapturedMomentServer.repository.VerifyCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* 2021-11-08 김예진*/
@Service
@Transactional
@AllArgsConstructor
public class VerifyCodeService {

    private final VerifyCodeRepository repository;

    public boolean check(VerifyCode code){
        VerifyCode findCode = repository.findByEmail(code.getEmail());
        if(findCode == null)
            return false;
        else if(code.getCode().equals(findCode.getCode()))
            return true;
        else
            return false;
    }

    public VerifyCode saveCode(VerifyCodeDto code){
        if(repository.existsByEmail(code.getEmail()))
            repository.deleteByEmail(code.getEmail());
        return repository.save(new VerifyCode(code));
    }

    public void deleteByEmail(String email){
        repository.deleteByEmail(email);
    }

    public VerifyCode findByCode(String code){
        VerifyCode verifyCode = repository.findByCode(code);
        if(verifyCode != null) repository.deleteByEmail(verifyCode.getEmail());
        return verifyCode;
    }
}
