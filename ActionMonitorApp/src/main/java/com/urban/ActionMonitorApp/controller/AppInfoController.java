package com.urban.ActionMonitorApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AppInfoController.class);

    @GetMapping(value = "/status")
    public String status() {
        LOG.info("Started gathering status");
//      TODO think about of returning status of different services (kafka etc)
        return "OK";
    }

    @GetMapping(value = "/version")
    public String version() {
        LOG.info("Started returning version");
//      TODO think about of returning versions of specific versions (kafka etc)
        return this.getClass().getPackage().getImplementationVersion();
    }

}