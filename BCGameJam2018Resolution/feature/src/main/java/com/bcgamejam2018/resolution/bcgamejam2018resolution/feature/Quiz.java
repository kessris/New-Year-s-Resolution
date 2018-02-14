package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

/**
 * Created by Eric Kim on 2018-02-10.
 */

public class Quiz {
    String information;
    String[] options;
    int correctIndex;
    String solution;
    public Quiz(String information, String[] options, String solution, int correctIndex) {
        this.information = information;
        this.options = options;
        this.solution = solution;
        this.correctIndex = correctIndex;
    }
}
