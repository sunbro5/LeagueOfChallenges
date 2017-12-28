package com.astora.web.service.impl;

import com.astora.web.dto.games.GamesInfoDto;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 8.12.2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/bean-conf.xml")
public class GameServiceImplTest {

    @Autowired
    private GameService gameService;


    @Test
    public void testGetGamesInformation() throws Exception {
        List<GamesInfoDto> list = gameService.getGamesInformation();
        assertNotNull(list);
    }

}