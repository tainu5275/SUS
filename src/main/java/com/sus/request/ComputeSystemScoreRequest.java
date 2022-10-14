package com.sus.request;

import java.util.List;
import java.util.Map;

public class ComputeSystemScoreRequest {

    Map<Integer,Integer> scores;

    public Map<Integer, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Integer, Integer> scores) {
        this.scores = scores;
    }
}
