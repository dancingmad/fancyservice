package com.irongrp.minesweeper.service.model;

import com.irongrp.minesweeper.service.exception.GeneralException;

import java.util.Random;

public class Board {

    private static int MINE_RATIO = 10;
    public static int MIN_SIZE = 5;
    public static int MAX_SIZE = 50;

    private Integer gameId;
    private MineField[][] mines;
    private int size;

    private Random random = new Random();

    public Board(int size) {
        this.size = size;
        this.gameId = Math.abs(random.nextInt());
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new GeneralException("Invalid Size");
        }
        createBoard();
    }

    public Integer getGameId() {
        return this.gameId;
    }

    public MineField[][] getMines() {
        return this.mines;
    }

    public void setMines(MineField[][] mines) {
        this.mines = mines;
        updateMineCounters();
    }

    private void createBoard() {
        mines = new MineField[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                mines[i][j] = createMine();
            }
        }
        updateMineCounters();
    }

    private void updateMineCounters() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if (mines[i][j].isMine()) {
                    mines[i][j].setMineCount(99);
                } else {
                    mines[i][j].setMineCount(calculateNeighborMineCount(i, j));
                }
            }
        }
    }

    private MineField createMine() {
        boolean isMine = Math.abs(random.nextInt())%MINE_RATIO == 0;
        return new MineField(isMine);
    }

    /********
     * we only care for direct neighbors, not diagonal
     * @param i
     * @param j
     * @return
     */
    private int calculateNeighborMineCount(int i, int j) {
        int count = 0;
        count += i > 0 && mines[i -1][j].isMine() ? 1 : 0;
        count += i < mines.length-1 && mines[i +1][j].isMine() ? 1 : 0;
        count += j > 0 && mines[i][j-1].isMine() ? 1 : 0;
        count += j < mines[i].length-1 && mines[i][j+1].isMine() ? 1 : 0;

        return count;
    }


}
