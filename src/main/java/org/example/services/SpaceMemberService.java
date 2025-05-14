package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberCreateRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceMemberService {
    SpaceMemberDto saveSpaceMember(SpaceMemberCreateRequest spaceMemberCreateRequest, UserDetails user);
    SpaceMemberDto updateSpaceMember(SpaceMemberUpdateRequest spaceMemberUpdateRequest, UserDetails user);
    SpaceMemberDto findSpaceMemberById(Long id, UserDetails user);
    void deleteSpaceMember(Long id, UserDetails user);
}
