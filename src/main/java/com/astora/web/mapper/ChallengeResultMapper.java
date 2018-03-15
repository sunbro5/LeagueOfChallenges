package com.astora.web.mapper;

import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.ChallengeResult;
import com.astora.web.dao.model.Team;
import com.astora.web.model.ChallengeResultModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 12.3.2018
 */
@Mapper
public interface ChallengeResultMapper {

    @Mappings({
            @Mapping(target = "challengeByChallengesChallengeId", source = "challenge"),
            @Mapping(target = "teamByWinnerTeamId", source = "winner"),
            @Mapping(target = "creator", source = "team"),
            @Mapping(target = "state", source = "challengeResultModel.state"),
            @Mapping(ignore = true, target = "created")
    })
    ChallengeResult challengeResultModelToChallengeResult(ChallengeResultModel challengeResultModel, Challenge challenge, Team winner, Team team);
}
