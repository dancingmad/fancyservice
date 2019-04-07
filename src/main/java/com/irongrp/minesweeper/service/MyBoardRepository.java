package com.irongrp.minesweeper.service;

import com.irongrp.minesweeper.service.model.Board;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyBoardRepository implements BoardRepository{

    private Map<Integer,Board> games = new HashMap<>();

    public void add(Board board) {
        games.put(board.getGameId(),board);
    }

    public List<Board> getBoards() {
        return new ArrayList<>(games.values());
    }

    public Board getBoard(Integer id) {
        return games.get(id);
    }


}
