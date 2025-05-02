package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceMemberService {
    SpaceMemberDto saveSpaceMember(SpaceMemberDto spaceMemberDto, UserDetails user);
    SpaceMemberDto updateSpaceMember(SpaceMemberDto spaceMemberDto, UserDetails user);
    SpaceMemberDto findSpaceMember(Long id);
    void removeSpaceMember(Long id);
}
