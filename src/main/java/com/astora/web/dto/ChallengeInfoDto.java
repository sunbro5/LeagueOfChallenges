package com.astora.web.dto;

import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 1.3.2018
 */
public class ChallengeInfoDto extends ChallengeDto {

    private List<String> challengers;
    private List<String> opponents;

    public List<String> getChallengers() {
        return challengers;
    }

    public void setChallengers(List<String> challengers) {
        this.challengers = challengers;
    }

    public List<String> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<String> opponents) {
        this.opponents = opponents;
    }
}
