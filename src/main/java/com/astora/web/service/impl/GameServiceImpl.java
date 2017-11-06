package com.astora.web.service.impl;

import com.astora.web.dao.GameDao;
import com.astora.web.dao.model.Game;
import com.astora.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("gameService")
@Transactional
public class GameServiceImpl implements GameService {

    private GameDao gameDao;

    @Autowired
    public void setRoleDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void create(Game game) {
        gameDao.create(game);
    }

    public void update(Game game) {
        gameDao.update(game);
    }

    public Game findById(int id) {
        return gameDao.findById(id);
    }

    public List<Game> findAll() {
        return gameDao.findAll();
    }

    public void delete(int id) {
        gameDao.delete(id);
    }
}
