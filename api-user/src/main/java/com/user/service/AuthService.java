package com.user.service;

import com.storage.entity.Account;
import com.storage.entity.User;
import com.storage.repository.AccountRepository;
import com.storage.repository.UserRepository;
import com.user.dto.request.UserDto.UserRegisterReq;
import com.user.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void register(UserRegisterReq request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이메일 중복 (이메일을 확인해주세요): " + request.getEmail());
        }

        // Account 엔티티 생성
        Account account = Account.builder()
                .email(request.getEmail())
                .password(SHA256.encrypt(request.getPassword()))
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
