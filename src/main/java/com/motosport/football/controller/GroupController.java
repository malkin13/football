package com.motosport.football.controller;

import com.motosport.football.model.Group;
import com.motosport.football.service.GroupService;
import com.motosport.football.service.IGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GroupController {

    IGroupService groupService;

    @RequestMapping("/showGrp")
    public String findCities(Model model) {

        List<Group> groups = (List<Group>) groupService.findAll();

        model.addAttribute("groups", groups);

        return "showGrp";
    }

}
