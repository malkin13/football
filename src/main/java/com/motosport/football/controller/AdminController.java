package com.motosport.football.controller;


import com.motosport.football.model.Group;
import com.motosport.football.repository.GroupRepository;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/group/{id}")
    public Group findById(@PathVariable int id) throws NotFoundException {
        Optional<Group> groupOptional = groupRepository.findById(id);

        if (!groupOptional.isPresent())
            throw new NotFoundException("can't find id"+id);

        return groupOptional.get();
    }

    @PostMapping("/groups")
    public ResponseEntity<Object> createGroup(@RequestBody Group group) {
        log.debug("create group");
        Group saveGroup = groupRepository.save(group);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveGroup.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Object> updateGroup(@RequestBody Group group, @PathVariable int id) {
        log.debug("update group");
        Optional<Group> groupOptional = groupRepository.findById(id);

        if (!groupOptional.isPresent())
            return ResponseEntity.notFound().build();

        group.setId(id);

        groupRepository.save(group);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable int id) {
        log.debug("delete group id:"+id);
        groupRepository.deleteById(id);
    }

    //TODO
    //
    // 1) Сделать контроллеры для Team и Game
    // 2) фикс создания group (exception is com.fasterxml.jackson.databind.exc.MismatchedInputException)

}
