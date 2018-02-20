package com.astora.web.mapper;

import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.Game;
import com.astora.web.dao.model.Team;
import com.astora.web.dto.ChallengeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 12.2.2018
 */
@Mapper
public interface ChallengeMapper {

    ChallengeDto challengeToChallengeDto(Challenge challenge);

    @Mappings({
            @Mapping(source = "team.name", target = "challengerTeamName"),
            @Mapping(source = "team.teamId", target = "challengerTeamId"),
            @Mapping(source = "opponent.teamId", target = "opponentTeamId")

    })
    ChallengeDto challengeToChallengeDto(Challenge challenge, Game game, Team team, Team opponent);

}
