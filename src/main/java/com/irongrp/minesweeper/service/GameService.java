package com.irongrp.minesweeper.service;

import com.irongrp.minesweeper.service.model.Board;
import com.irongrp.minesweeper.service.model.MineField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GameService {

    @Autowired
    private BoardRepository boardRepository;

    public Board startGame(int size) {
        Board board = new Board(size);
        boardRepository.add(board);
        return board;
    }

    public Board updateBoard(Integer gameId,
                             boolean[][] revealed,
                             boolean[][] marked) {
        // here we match the revealed with the saved board
        // if a revealed has no mines around it and a revealed is next to it,
        // then it is revealed (do until no more get revealed)
        // also save the marked for no revealed ones
        Board board = boardRepository.getBoard(gameId);
        patchFields(board.getMines(),revealed,marked);
        revealZeroMineCountNeighbors(board.getMines());
        return board;
    }

    private void patchFields(MineField[][] mines,
                             boolean[][] revealed,
                             boolean[][] marked) {
        for(int i=0;i<revealed.length;i++) {
            for(int j=0;j<revealed.length;j++) {
                mines[i][j].setRevealed(revealed[i][j]);
                mines[i][j].setMarked(marked[i][j]);
            }
        }
    }

    private void revealZeroMineCountNeighbors(MineField[][] mines) {
        boolean revealedNewField;
        do {
            revealedNewField = false;
            for (int i = 0; i < mines.length; i++) {
                for (int j = 0; j < mines.length; j++) {
                    if (mines[i][j].isRevealed()) {
                        mines[i][j].setShowCount(true);
                        revealedNewField = revealedNewField || processNeighbor(mines, i - 1, j);
                        revealedNewField = revealedNewField || processNeighbor(mines, i + 1, j);
                        revealedNewField = revealedNewField || processNeighbor(mines, i, j - 1);
                        revealedNewField = revealedNewField || processNeighbor(mines, i, j + 1);
                    }
                }
            }
        } while(revealedNewField);
    }

    private boolean processNeighbor(MineField[][] mines, int i, int j) {
        if (i < 0 || i >= mines.length) {
            return false;
        }
        if (j < 0 || j >= mines[i].length) {
            return false;
        }
        MineField mine = mines[i][j];
        if (mine.getMineCount() == 0 && !mine.isRevealed() && !mine.isMine()) {
            mine.setRevealed(true);
            return true;
        }
        mine.setShowCount(true);
        return false;
    }


    public Board getBoard(Integer id) {
        return boardRepository.getBoard(id);
    }

    public List<Board> getBoards() {
        return boardRepository.getBoards();
    }
}
