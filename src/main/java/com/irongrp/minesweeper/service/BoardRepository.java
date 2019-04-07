package com.irongrp.minesweeper.service;

import com.irongrp.minesweeper.service.model.Board;

import java.util.ArrayList;
import java.util.List;

public interface BoardRepository {

    void add(Board board);
    List<Board> getBoards();
    Board getBoard(Long id);
}
