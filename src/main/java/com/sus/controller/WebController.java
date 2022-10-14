package com.sus.controller;

import com.sus.dto.ScoreDetailsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class WebController {

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("questions", buildListOfQuestions());
        String randomID = String.valueOf(UUID.randomUUID());
        model.addAttribute("sessionID", randomID);
        return "index";
    }

    @PostMapping(value = "/test")
    public void test(@ModelAttribute String[] array){
        System.out.println("");
    }

    private String[] buildListOfQuestions() {
        String[] questionArray = new String[10];
        questionArray[0] = "I think that I would like to use this system frequently.";
        questionArray[1] = "I found the system unnecessarily complex.";
        questionArray[2] = "I thought the system was easy to use.";
        questionArray[3] = "I think that I would need the support of a technical person to be able to use this system.";
        questionArray[4] = "I found the various functions in this system were well integrated.";
        questionArray[5] = "I thought there was too much inconsistency in this system.";
        questionArray[6] = "I would imagine that most people would learn to use this system very quickly.";
        questionArray[7] = "I found the system very cumbersome to use.";
        questionArray[8] = "I felt very confident using the system.";
        questionArray[9] = "I needed to learn a lot of things before I could get going with this system.";
        return questionArray;
    }

}
