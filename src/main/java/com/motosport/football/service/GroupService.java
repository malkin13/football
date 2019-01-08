package com.motosport.football.service;

import com.motosport.football.model.Group;
import com.motosport.football.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static com.motosport.football.config.SoccerConstants.QUEUE_DEFAULT_SIZE;


public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

   // private final Logger log = LoggerFactory.getLogger(GroupService.class);

   // private Queue<Timestamp> timestampQueue = new ArrayBlockingQueue<>(QUEUE_DEFAULT_SIZE);

    private volatile long nextDbCheck = System.currentTimeMillis();

    public List<Group> findAll() {

        List<Group> group = groupRepository.findAll();

        return group;
    }


}
