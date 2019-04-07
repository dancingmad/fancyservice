package com.irongrp.minesweeper.api;

import com.irongrp.minesweeper.api.model.BoardDTO;
import com.irongrp.minesweeper.api.model.BoardField;
import com.irongrp.minesweeper.api.model.BoardMapper;
import com.irongrp.minesweeper.service.GameService;
import com.irongrp.minesweeper.service.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MinesweeperBoardResourceTest {

    @Mock
    private GameService gameService;

    @Spy
    private BoardMapper boardMapper;

    @InjectMocks
    private MinesweeperBoardResource minesweeperBoardResource;

    @Test
    public void createGame() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setSize(5);
        Board board = new Board(5);
        Mockito.when(gameService.startGame(5)).thenReturn(board);
        BoardDTO result = minesweeperBoardResource.createGame(boardDTO);
        assertNonRevealed(result.getFields());
        assertEquals(BoardDTO.Status.PLAY,result.getStatus());
    }

    @Test
    public void updateGame() throws Exception {
        Board board = new Board(5);
        Integer gameId = board.getGameId();
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setFields(createFields(5, new BoardField()));
        boardDTO.setSize(5);
        boardDTO.setGameId(gameId);
        boolean[][] revealed = new boolean[5][5];
        boolean[][] marked = new boolean[5][5];
        Mockito.when(gameService.updateBoard(eq(gameId),eq(revealed), eq(marked))).thenReturn(board);
        BoardDTO result = minesweeperBoardResource.updateGame(boardDTO);
        assertEquals(gameId, result.getGameId());
        assertEquals(5, result.getFields().length);
        assertEquals(BoardDTO.Status.PLAY, result.getStatus());
    }



    @Test
    public void getBoard() throws Exception {
        Board board = new Board(5);
        Integer gameId = board.getGameId();
        when(gameService.getBoard(gameId)).thenReturn(board);
        BoardDTO result = minesweeperBoardResource.getBoard(gameId);
        assertEquals(gameId, result.getGameId());
        assertEquals(5, result.getFields().length);
        assertEquals(BoardDTO.Status.PLAY, result.getStatus());
    }

    @Test
    public void getBoards() throws Exception {
        minesweeperBoardResource.getBoards();
    }

    private BoardField[][] createFields(int size, BoardField field) {
        BoardField[][] fields = new BoardField[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                fields[i][j]=field;
            }
        }
        return fields;
    }

    private void assertNonRevealed(BoardField[][] boardFields) {
        for(BoardField[] fields:boardFields) {
            for(BoardField field:fields) {
                assertFalse(field.isRevealed());
            }
        }
    }
}