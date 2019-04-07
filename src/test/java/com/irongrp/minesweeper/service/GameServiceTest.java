package com.irongrp.minesweeper.service;

import com.irongrp.minesweeper.service.model.Board;
import com.irongrp.minesweeper.service.model.MineField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    public void startNewGame() throws Exception {
        Board board = gameService.startGame(20);
        assertNotNull(board.getGameId());
        Assert.assertEquals(20, board.getMines().length);
        Assert.assertEquals(20, board.getMines()[0].length);
        assertNoneRevealed(board.getMines());
    }

    @Test
    public void updateBoard() throws Exception {
        Board testBoard = new Board(5);
        Long gameId = testBoard.getGameId();
        when(boardRepository.getBoard(gameId)).thenReturn(testBoard);
        MineField[][] mines = testBoard.getMines();
        clearBoard(mines);
        mines[1][1] = new MineField(true);
        testBoard.setMines(mines);
        boolean[][] revealed = new boolean[5][5];
        boolean[][] marked = new boolean[5][5];
        revealed[4][4] = true;
        Board result = gameService.updateBoard(gameId, revealed, marked);
        Assert.assertFalse(result.getMines()[0][0].isRevealed());
        Assert.assertFalse(result.getMines()[0][0].isShowCount());
        Assert.assertTrue(result.getMines()[1][0].isShowCount());
        Assert.assertTrue(result.getMines()[0][1].isShowCount());
        Assert.assertTrue(result.getMines()[2][2].isRevealed());

    }

    @Test
    public void getBoard() throws Exception {
        Board mockBoard = mock(Board.class);
        when(boardRepository.getBoard(123L)).thenReturn(mockBoard);
        Board result = gameService.getBoard(123L);
        assertEquals(mockBoard,result);
    }

    @Test
    public void getBoards() throws Exception {
        Board mockBoard = mock(Board.class);
        when(boardRepository.getBoards()).thenReturn(Collections.singletonList(mockBoard));
        List<Board> result = gameService.getBoards();
        assertEquals(1,result.size());
        assertEquals(mockBoard,result.get(0));
    }

    private void assertNoneRevealed(MineField[][] mineFields) {
        for(MineField[] mines:mineFields) {
            for(MineField mine:mines) {
                Assert.assertFalse(mine.isRevealed());
            }
        }
    }

    private void clearBoard(MineField[][] mineFields) {
       for(int i=0;i<mineFields.length;i++) {
           for(int j=0;j<mineFields.length;j++) {
               mineFields[i][j] = new MineField(false);
           }
       }
    }

    private boolean[][] createArray(int size) {
        return new boolean[size][size];
    }
}