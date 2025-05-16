package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.UserEntity;
import org.example.exceptions.InvalidRequestParameterException;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceMemberRepository;
import org.example.services.AuthClientService;
import org.example.services.SpaceMemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpaceMemberServiceImpl implements SpaceMemberService {
    private final AuthClientService authService;
    private final SpaceMemberRepository spaceMemberRepository;
    private final Mapper<SpaceMemberEntity, SpaceMemberDto> responseMapper;
    private final Mapper<SpaceMemberEntity, SpaceMemberAddRequest> createRequestMapper;


    @Override
    public SpaceMemberDto addSpaceMember(SpaceMemberAddRequest request, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;

        if (!authService.checkUserIdExists(request.getUserId())) {
            throw new EntityNotFoundException("User with id " + request.getUserId() + " was not found");
        }

        if (spaceMemberRepository.findById(request.getUserId()).isPresent()) {
            throw new InvalidRequestParameterException("Space member id " + request.getUserId() + " already exists in this space");
        }

        SpaceMemberEntity newMember = spaceMemberRepository.save(createRequestMapper.mapFromDto(request)
                .setInvitedByMember(spaceMemberRepository.findById(userEntity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userEntity.getUserId() + " not found"))
                ));

        return responseMapper.mapToDto(newMember);
    }

    @Override
    public SpaceMemberDto updateSpaceMember(SpaceMemberUpdateRequest request, UserDetails user, Long spaceMemberId) {
        UserEntity userEntity = (UserEntity) user;

        SpaceMemberEntity memberEntity = spaceMemberRepository.findById(spaceMemberId)
                .orElseThrow(() -> new EntityNotFoundException("Member " + spaceMemberId + " not found"));

        memberEntity.setRole(request.getRole());

        return responseMapper.mapToDto(spaceMemberRepository.saveAndFlush(memberEntity));
    }

    @Override
    public SpaceMemberDto findSpaceMemberById(Long id, UserDetails user) {
        return responseMapper.mapToDto(spaceMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member " + id + " not found")));
    }

    @Override
    public void deleteSpaceMember(Long id, UserDetails user) {
        spaceMemberRepository.deleteById(id);
    }
}
