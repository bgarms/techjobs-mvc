package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
//  Request Path: /search
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    @RequestMapping(value = "results", method = RequestMethod.GET)

    // Request Path: /search/results

    public String search (Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

        model.addAttribute("columns", ListController.columnChoices);

        ArrayList<HashMap<String, String>> jobs;

        //If Search Type is All --- jobs is created and all of the data in JobData
        // is sent to web.

        if (searchType.equals("all")){
            jobs = JobData.findAll();

            if(searchTerm != ""){

                // Will send all data containing searchTerm to the web.

                jobs = JobData.findByValue(searchTerm);
            }

            // Otherwise, jobs is created with the data based on searchType and searchTerm
            // and sent to the web.

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("title", "Results");
        model.addAttribute("searchInfo", jobs);



        return "search";

    }
}

