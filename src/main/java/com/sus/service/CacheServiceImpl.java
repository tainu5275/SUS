package com.sus.service;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheServiceImpl implements CacheService{

    Map<String, Double> sessionScoreLiveCacheObject;

    @Override
    public double getSessionScore(String sessionID) {
        if(!CollectionUtils.isEmpty(this.sessionScoreLiveCacheObject)
                && sessionScoreLiveCacheObject.containsKey(sessionID)){
            return sessionScoreLiveCacheObject.get(sessionID);
        } else {
            return 0;
        }
    }

    @Override
    public void putSessionScore(String sessionId, double score) {
        if(null == this.sessionScoreLiveCacheObject) {
            sessionScoreLiveCacheObject = new HashMap<>();
        }
        sessionScoreLiveCacheObject.put(sessionId,score);
    }
}
