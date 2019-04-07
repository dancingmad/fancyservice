package com.irongrp.minesweeper.api;

import com.irongrp.minesweeper.api.model.BoardDTO;
import com.irongrp.minesweeper.api.model.BoardMapper;
import com.irongrp.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/minesweeper")
public class MinesweeperBoardResource {

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardMapper boardMapper;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public BoardDTO createGame(BoardDTO board) {
        return boardMapper.map(gameService.startGame(board.getSize()));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public BoardDTO updateGame(BoardDTO board) {
        return boardMapper.map(gameService.updateBoard(board.getGameId(),
                getRevealed(board),
                getMarked(board)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BoardDTO getBoard(@PathVariable Long id) {
        return boardMapper.map(gameService.getBoard(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<BoardDTO> getBoards() {
        return gameService.getBoards().stream()
                .map(boardMapper::map)
                .collect(Collectors.toList());
    }

    private boolean[][] getRevealed(BoardDTO boardDTO) {
        int boardSize = boardDTO.getSize();
        boolean[][] revealed = new boolean[boardSize][boardSize];
        for(int i=0;i<boardSize;i++) {
            for(int j=0;j<boardSize;j++) {
                revealed[i][j]=boardDTO.getFields()[i][j].isRevealed();
            }
        }
        return revealed;
    }

    private boolean[][] getMarked(BoardDTO boardDTO) {
        int boardSize = boardDTO.getSize();
        boolean[][] marked = new boolean[boardSize][boardSize];
        for(int i=0;i<boardSize;i++) {
            for(int j=0;j<boardSize;j++) {
                marked[i][j]=boardDTO.getFields()[i][j].isMarked();
            }
        }
        return marked;
    }


}
