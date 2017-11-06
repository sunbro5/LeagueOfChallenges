package com.astora.web.service;

import com.astora.web.dao.model.Game;

import java.util.List;

public interface GameService {
    void create(Game game);

    void update(Game game);

    Game findById(int id);

    List<Game> findAll();

    void delete(int id);
}
