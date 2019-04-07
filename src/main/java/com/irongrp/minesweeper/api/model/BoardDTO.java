package com.irongrp.minesweeper.api.model;

public class BoardDTO {

    public enum Status {
        PLAY,END_WIN,END_LOSE
    }

    private Status status;
    private BoardField[][] fields;
    private Integer gameId;
    private int size;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
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
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }




}
