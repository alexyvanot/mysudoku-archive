package com.intech.mysudoku.application;

/**
 * Launcher class to bypass JavaFX module restrictions when running from JAR.
 * This class is needed because JavaFX 11+ requires the main class to NOT extend Application
 * when running from a fat JAR without module-path.
 */
public class Launcher {
    public static void main(String[] args) {
        MySudokuApp.main(args);
    }
}
