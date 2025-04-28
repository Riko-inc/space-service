package org.example.security;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.responses.UserDetailResponse;
import org.example.domain.entities.UserEntity;
import org.example.services.AuthClientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final AuthClientService authClientService;

    @Override
    public UserDetails loadUserByUsername(String token) {
        UserDetailResponse response = authClientService.getUserDetail();
        return UserEntity
                .builder()
                .role(response.getAuthorities())
                .userId(response.getUserId())
                .email(response.getUsername())
                .password(response.getPassword())
                .isActive(response.isEnabled())
                .build();
    }
}