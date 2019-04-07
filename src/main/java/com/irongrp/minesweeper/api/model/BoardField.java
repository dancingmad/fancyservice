package com.irongrp.minesweeper.api.model;

public class BoardField {
    private int mineCount;
    private boolean revealed;
    private boolean kill;
    private boolean marked;

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
