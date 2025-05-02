package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.UserEntity;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceMemberRepository;
import org.example.services.SpaceMemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SpaceMemberServiceImpl implements SpaceMemberService {
    private final SpaceMemberRepository spaceMemberRepository;
    private final Mapper<SpaceMemberEntity, SpaceMemberDto> mapper;
    @Override
    public SpaceMemberDto saveSpaceMember(SpaceMemberDto spaceMemberDto, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        return mapper.mapToDto(spaceMemberRepository.save(
                mapper.mapFromDto(spaceMemberDto)
                        .setInvitedByMember(spaceMemberRepository.findById(userEntity.getUserId()).orElse(null))
                        .setInvitedDateTime(LocalDateTime.now())
        ));
    }

    @Override
    public SpaceMemberDto updateSpaceMember(SpaceMemberDto spaceMemberDto, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        return mapper.mapToDto(spaceMemberRepository.save(mapper.mapFromDto(spaceMemberDto)));
    }

    @Override
    public SpaceMemberDto findSpaceMemberById(Long id) {
        return mapper.mapToDto(spaceMemberRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteSpaceMember(Long id) {
        spaceMemberRepository.deleteById(id);
    }
}
