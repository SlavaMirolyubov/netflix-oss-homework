package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.WorkspaceAPI;
import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.services.WorkplaceService;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/workspaces")
public class WorkplaceAPIController implements WorkspaceAPI {

    @Autowired
    private WorkplaceService workplaceService;

    Logger log = LogManager.getLogger(Log4j.class);

    @RequestMapping("/{id}")
    public Workspace getWorkspaceById(@PathVariable("id") String id) {
        log.info("Instance {} received workspace request", this);
        return workplaceService.findWorkspace(id);
    }
}
