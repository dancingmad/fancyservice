package com.irongrp.minesweeper.api.model;

public class BoardDTO {

    public enum Status {
        PLAY,END_WIN,END_LOSE
    }

    private Status status;
    private BoardField[][] fields;
    private long gameId;
    private int size;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BoardField[][] getFields() {
        return fields;
    }

    public void setFields(BoardField[][] fields) {
        this.fields = fields;
        this.size = fields.length;
    }

    public int getSize() {
        return this.size;
    }




}
