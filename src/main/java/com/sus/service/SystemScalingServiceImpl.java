package com.sus.service;

import com.sus.domain.ScoreBoard;
import com.sus.dto.ScoreDetailsDto;
import com.sus.exception.SystemScoreCustomExceptions;
import com.sus.repo.ScoreBoardDao;
import com.sus.request.ComputeSystemScoreRequest;
import com.sus.response.GlobalScoreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SystemScalingServiceImpl implements SystemScalingService{

    private static final Logger LOG = LoggerFactory.getLogger(SystemScalingService.class);

    @Autowired
    private ScoreBoardDao scoreBoardDao;

    @Autowired
    private CacheService cacheService;

    public double computeAndSaveScoreForSingleSession(ComputeSystemScoreRequest computeSystemScoreRequest, String sessionID){

        LocalDateTime localDateTime = LocalDateTime.now();
        int scaleSum = getScaleSum(computeSystemScoreRequest.getScores());
        double systemScore = scaleSum * 2.5;

        ScoreBoard scoreBoard = new ScoreBoard(sessionID, systemScore, localDateTime);
        ScoreBoard response = scoreBoardDao.save(scoreBoard);

        if(response != null){
            LOG.debug("Computed scoring of a system is saved successfully");
            cacheService.putSessionScore(sessionID, systemScore);
            return systemScore;
        } else {
            LOG.error("Saving the computed score is failed");
            throw new SystemScoreCustomExceptions.CalculateScoreException(
                    "Technical issue while calculating the score", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    private int getScaleSum(Map<Integer, Integer> scores) {
        int scaleSum = 0;
        for(Map.Entry<Integer,Integer> entry : scores.entrySet()) {

            if(entry.getKey() % 2 ==0){
                scaleSum = scaleSum + (entry.getValue()- 1);
            }
            else {
                scaleSum = scaleSum + (5- entry.getValue());
            }

        }
        return scaleSum;
    }

    public GlobalScoreResponse getGlobalScoreData(){

        List<ScoreBoard> scoreBoardList = scoreBoardDao.findAll();
        double total = 0;
        GlobalScoreResponse globalScoreResponse = new GlobalScoreResponse();
        if(null != scoreBoardList && !scoreBoardList.isEmpty()) {
            for(ScoreBoard scoreBoard: scoreBoardList){
                total = total + scoreBoard.getScore();
            }
            globalScoreResponse.setScore(total/scoreBoardList.size());
            globalScoreResponse.setEntries(scoreBoardList.size());
        }

        return globalScoreResponse;
    }

    @Override
    public double getSessionScore(String sessionId) {
        double scoreFromCache = cacheService.getSessionScore(sessionId);
        if(0 == scoreFromCache) {
            Optional<ScoreBoard> scoreBoard = scoreBoardDao.findById(sessionId);
            if (scoreBoard.isPresent()) {
                double score = scoreBoard.get().getScore();
                cacheService.putSessionScore(sessionId,score);
                return score;
            } else {
                throw  new SystemScoreCustomExceptions.LocalizedSessionScoreException("Invalid session ID",
                        HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            return scoreFromCache;
        }
    }

}
