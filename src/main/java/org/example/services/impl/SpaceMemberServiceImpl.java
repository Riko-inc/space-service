package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.example.exceptions.InvalidRequestParameterException;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceMemberRepository;
import org.example.repositories.WorkspaceRepository;
import org.example.services.AuthClientService;
import org.example.services.SpaceMemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SpaceMemberServiceImpl implements SpaceMemberService {
    private final AuthClientService authService;
    private final WorkspaceRepository workspaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;
    private final Mapper<SpaceMemberEntity, SpaceMemberDto> responseMapper;
    private final Mapper<SpaceMemberEntity, SpaceMemberAddRequest> createRequestMapper;


    @Override
    public SpaceMemberDto addSpaceMember(UserDetails user, SpaceMemberAddRequest request, Long workspaceId) {
        UserEntity userEntity = (UserEntity) user;

        WorkspaceEntity workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ workspaceId +" not found"));

        // Проверка, что пользователь, которого добавляем существует в auth-service
        if (!authService.checkUserIdExists(request.getUserId())) {
            throw new EntityNotFoundException("User with id " + request.getUserId() + " not found");
        }

        // Проверка, что пользователь, которого добавляем не состоит в пространстве
        if (spaceMemberRepository.findSpaceMemberEntityByUserIdAndWorkspace(request.getUserId(), workspace).isPresent()) {
            throw new InvalidRequestParameterException("Space member with user id " + request.getUserId() + " already exists in this space");
        }

        SpaceMemberEntity inviterSpaceEntity = spaceMemberRepository.findSpaceMemberEntityByUserIdAndWorkspace(userEntity.getUserId(), workspace)
                .orElseThrow(() -> new EntityNotFoundException("Invited user with id " + userEntity.getUserId() + " not found"));

        request.setInvitedByMemberId(inviterSpaceEntity.getSpaceMemberId());
        request.setWorkspaceId(workspaceId);

        log.info("Created space member entity from request DTO: {}", createRequestMapper.mapFromDto(request));

        SpaceMemberEntity newEntity = spaceMemberRepository.save(createRequestMapper.mapFromDto(request));

        workspace.getMembers().add(newEntity);
        workspaceRepository.save(workspace);
        log.info("New space member entity: {}", newEntity);

        return responseMapper.mapToDto(newEntity);
    }

    @Override
    public SpaceMemberDto updateSpaceMember(UserDetails user, SpaceMemberUpdateRequest request, Long workspaceId, Long spaceMemberId) {
        UserEntity userEntity = (UserEntity) user;

        SpaceMemberEntity memberEntity = spaceMemberRepository.findById(spaceMemberId)
                .orElseThrow(() -> new EntityNotFoundException("Member " + spaceMemberId + " not found"));

        memberEntity.setRole(request.getRole());

        return responseMapper.mapToDto(spaceMemberRepository.save(memberEntity));
    }

    @Override
    public SpaceMemberDto findSpaceMemberById(UserDetails user, Long spaceMemberId, Long workspaceId) {
        WorkspaceEntity workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ workspaceId +" not found"));

        return responseMapper.mapToDto(spaceMemberRepository.findSpaceMemberEntityBySpaceMemberIdAndWorkspace(spaceMemberId, workspace)
                .orElseThrow(() -> new EntityNotFoundException("Member " + spaceMemberId + " not found")));
    }

    // TODO: Накинуть на контроллеры проверку прав просмотра
    @Override
    public List<SpaceMemberDto> findAllSpaceMembers(UserDetails user, Long workspaceId) {
        UserEntity userEntity = (UserEntity) user;
        WorkspaceEntity workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ workspaceId +" not found"));

        return spaceMemberRepository.findByWorkspace(workspace)
                .stream().map(responseMapper::mapToDto).toList();
    }

    @Override
    public SpaceMemberDto findByUserIdAndSpace(UserDetails user, Long workspaceId) {
        UserEntity userEntity = (UserEntity) user;
        WorkspaceEntity workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ workspaceId +" not found"));


        return responseMapper.mapToDto(spaceMemberRepository.findByUserIdAndWorkspace(userEntity.getUserId(), workspace)
                .orElseThrow(() -> new EntityNotFoundException("User with id "+ userEntity.getUserId() +" was not found in this space")));
    }

    @Override
    public void deleteSpaceMember(UserDetails user, Long spaceMemberId, Long workspaceId) {
        spaceMemberRepository.deleteById(spaceMemberId);
    }
}
