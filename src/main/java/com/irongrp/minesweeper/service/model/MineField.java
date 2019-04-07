package com.irongrp.minesweeper.service.model;

public class MineField {

    private int mineCount;
    private boolean mine;
    private boolean revealed;
    private boolean marked;
    private boolean showCount;


    public MineField(boolean mine) {
        this.mine = mine;
        this.showCount = false;
        revealed = false;
        marked = false;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isShowCount() {
        return showCount;
    }

    public void setShowCount(boolean showCount) {
        this.showCount = showCount;
    }
}
