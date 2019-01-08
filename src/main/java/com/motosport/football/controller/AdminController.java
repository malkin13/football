package com.motosport.football.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@Slf4j
@RestController
@RequestMapping("/")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    @GetMapping("create")
    public ResponseEntity<String> create() {
        log.debug("create group");
        try {
            // context.getBean(TcpBalancer.class).reloadTcpClients();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("done", HttpStatus.OK);
    }


}
*/