package com.sus.service;

public interface CacheService {

    public double getSessionScore(String sessionID);
    public void putSessionScore(String sessionId, double score);
}
