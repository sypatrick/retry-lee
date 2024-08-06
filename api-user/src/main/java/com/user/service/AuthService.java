package com.user.service;

import com.storage.entity.Account;
import com.storage.entity.User;
import com.storage.repository.AccountRepository;
import com.storage.repository.UserRepository;
import com.user.dto.request.UserDto.UserRegisterReq;
import com.user.exception.CustomException;
import com.user.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    public void register(UserRegisterReq request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.ERROR_BE1001);
        }

        // Account 엔티티 생성
        Account account = Account.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        // User 엔티티 생성
        User user = User.builder()
                .nickname(request.getNickname())
                .account(account)
                .grade("silver")
                .build();

        // 저장
        accountRepository.save(account);
        userRepository.save(user);
    }
}
