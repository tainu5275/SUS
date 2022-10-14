package com.sus.controller;

import antlr.StringUtils;
import com.sus.dto.ScoreDetailsDto;
import com.sus.exception.SystemScoreCustomExceptions;
import com.sus.request.ComputeSystemScoreRequest;
import com.sus.response.GlobalScoreResponse;
import com.sus.service.SystemScalingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sus")
public class SystemScoreController {

    private static final Logger LOG = LoggerFactory.getLogger(SystemScoreController.class);

    @Autowired
    private SystemScalingService systemScalingService;

    @PostMapping(value = "/compute/{sessionID}")
    public String computeSessionScore(@PathVariable("sessionID") String sessionID,
                                      @ModelAttribute(value = "scores") ComputeSystemScoreRequest computeSystemScoreRequest,
                                      Model modelMap){
        
        if(null == sessionID || sessionID.isBlank()) {
            LOG.error("Invalid session ID");
            throw  new SystemScoreCustomExceptions.CalculateScoreException(
                    "Invalid session ID provided", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        LOG.info("About to start computing the score for session {}", sessionID);
        double score = systemScalingService.computeAndSaveScoreForSingleSession(computeSystemScoreRequest, sessionID);
        modelMap.addAttribute("currentScore", score);
        modelMap.addAttribute("sessionID",sessionID);
        return "currentScore";
    }


   @GetMapping("/global-score/{sessionID}")
    public String computeGlobalScore(@PathVariable("sessionID") String sessionID, Model model){
       LOG.info("About to start computing the global score by session {}", sessionID);
        GlobalScoreResponse globalScoreResponse = systemScalingService.getGlobalScoreData();
        model.addAttribute("scores", globalScoreResponse);
        model.addAttribute("sessionID", sessionID);
        return  "globalScore";
    }

    @GetMapping("/getScore/{sessionID}")
    public String getSessionScore(@PathVariable("sessionID") String sessionID, ModelMap modelMap) {
        if(null == sessionID || sessionID.isBlank()) {
            throw  new SystemScoreCustomExceptions.LocalizedSessionScoreException("Invalid session ID",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        LOG.info("About to get the local session score for {}", sessionID);
        double score = systemScalingService.getSessionScore(sessionID);
        modelMap.addAttribute("currentScore",score);
        return "currentScore";
    }


}
