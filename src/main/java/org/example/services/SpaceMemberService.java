package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceMemberService {
    SpaceMemberDto addSpaceMember(SpaceMemberAddRequest spaceMemberAddRequest, UserDetails user);
    SpaceMemberDto updateSpaceMember(SpaceMemberUpdateRequest spaceMemberUpdateRequest, UserDetails user, Long spaceMemberId);
    SpaceMemberDto findSpaceMemberById(Long id, UserDetails user);
    void deleteSpaceMember(Long id, UserDetails user);
}
