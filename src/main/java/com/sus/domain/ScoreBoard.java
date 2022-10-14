package com.sus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "score_board")
public class ScoreBoard {

    @Id
    private String sessionId;

    @Column(name = "score")
    private double score;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    public ScoreBoard(){
        
    }

    public ScoreBoard(String sessionId, double systemScore, LocalDateTime createdDateTime) {
        this.sessionId = sessionId;
        this.score = systemScore;
        this.createdDateTime = createdDateTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public double getScore() {
        return score;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
}
