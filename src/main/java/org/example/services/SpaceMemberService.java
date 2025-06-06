package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceMemberService {
    SpaceMemberDto addSpaceMember(UserDetails user, SpaceMemberAddRequest spaceMemberAddRequest, Long workspaceId);
    SpaceMemberDto updateSpaceMember(UserDetails user, SpaceMemberUpdateRequest spaceMemberUpdateRequest, Long workspaceId, Long spaceMemberId);
    SpaceMemberDto findSpaceMemberById(UserDetails user, Long spaceMemberId, Long workspaceId);
    void deleteSpaceMember(UserDetails user, Long spaceMemberId, Long workspaceId);
}
