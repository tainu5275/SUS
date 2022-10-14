package com.sus.service;

import com.sus.request.ComputeSystemScoreRequest;
import com.sus.response.GlobalScoreResponse;

public interface SystemScalingService {

    public double computeAndSaveScoreForSingleSession(ComputeSystemScoreRequest computeSystemScoreRequest, String sessionId);
    public GlobalScoreResponse getGlobalScoreData();
    public double getSessionScore(String sessionId);
}
