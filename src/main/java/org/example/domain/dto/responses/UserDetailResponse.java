package org.example.domain.dto.responses;

import lombok.*;
import org.example.domain.entities.UserEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResponse {
    UserEntity.Role authorities;
    Long userId;
    String password;
    String username;
    boolean isAccountNonExpired;
    boolean isAccountNonLocked;
    boolean isCredentialsNonExpired;
    boolean isEnabled;
}
