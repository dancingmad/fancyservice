package com.irongrp.minesweeper.service;

import com.irongrp.minesweeper.service.model.Board;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class MyBoardRepositoryTest {


    @Test
    public void add() throws Exception {
        Board board = Mockito.mock(Board.class);
        new MyBoardRepository().add(board);
    }

    @Test
    public void getBoards() throws Exception {
        MyBoardRepository repo = new MyBoardRepository();
        Board board = Mockito.mock(Board.class);
        repo.add(board);
        List<Board> result = repo.getBoards();
        assertEquals(1,result.size());
        assertEquals(board, result.get(0));
    }

    @Test
    public void getBoard() throws Exception {
        MyBoardRepository repo = new MyBoardRepository();
        Board board = Mockito.mock(Board.class);
        Mockito.when(board.getGameId()).thenReturn(111);
        repo.add(board);
        Board result = repo.getBoard(111);
        assertEquals(board,result);

    }

}