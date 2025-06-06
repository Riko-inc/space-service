package org.example.config;

import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.entities.SpaceMemberEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);

        modelMapper.createTypeMap(SpaceMemberAddRequest.class, SpaceMemberEntity.class)
                .addMappings(m -> {
                    m.skip(SpaceMemberEntity::setSpaceMemberId);
                    m.skip(SpaceMemberEntity::setWorkspace);
                    m.skip(SpaceMemberEntity::setInvitedByMember);
                });

        return modelMapper;
    }
}
