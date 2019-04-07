package com.irongrp.minesweeper.service.model;

import com.irongrp.minesweeper.service.exception.GeneralException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {


    @Test
    public void exceedingMaxSize() {
        try {
            new Board(Board.MAX_SIZE+1);
            fail("Size should fail");
        } catch(GeneralException e) {
            // is fine
        }
    }

    @Test
    public void exceedingMinSize() {
        try {
            new Board(Board.MIN_SIZE-1);
            fail();
        } catch(GeneralException e) {
            // is fine
        }
    }

    @Test
    public void correctSize() {
        new Board(Board.MAX_SIZE-1);
    }
}