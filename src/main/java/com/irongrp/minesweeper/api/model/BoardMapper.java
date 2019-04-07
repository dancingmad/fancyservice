package com.irongrp.minesweeper.api.model;

import com.irongrp.minesweeper.service.model.Board;
import com.irongrp.minesweeper.service.model.MineField;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {


    public BoardDTO map(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setGameId(board.getGameId());
        boardDTO.setStatus(getStatus(board));
        boardDTO.setFields(map(board.getMines()));
        boardDTO.setSize(board.getMines().length);
        return boardDTO;
    }

    private BoardField[][] map(MineField[][] mineFields) {
        BoardField[][] fields = new BoardField[mineFields.length][mineFields.length];
        for (int i = 0; i < mineFields.length; i++) {
            for (int j = 0; j < mineFields.length; j++) {
                fields[i][j] = map(mineFields[i][j]);
            }
        }
        return fields;
    }

    private BoardField map(MineField mineField) {
        BoardField field = new BoardField();
        field.setMineCount(mineField.isShowCount() && !mineField.isMine() ? mineField.getMineCount():-1);
        field.setKill(mineField.isRevealed() && mineField.isMine());
        field.setRevealed(mineField.isRevealed());
        field.setMarked(mineField.isMarked());
        return field;
    }

    private BoardDTO.Status getStatus(Board board) {
        MineField[][] mineFields = board.getMines();
        for(MineField[] mines : mineFields) {
            for(MineField mine : mines) {
                if (mine.isRevealed() && mine.isMine()) {
                    return BoardDTO.Status.END_LOSE;
                }
                if (!mine.isShowCount() && !mine.isMine()) {
                    return BoardDTO.Status.PLAY;
                }
             }
        }
        for(MineField[] mines : mineFields) {
            for(MineField mine : mines) {
                mine.setRevealed(true);
            }
        }
        return BoardDTO.Status.END_WIN;
    }

}
