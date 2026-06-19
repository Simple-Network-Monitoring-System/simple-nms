package com.nms.simplenms.controller;

import com.nms.routersimulator.model.RouterMetric;
import com.nms.simplenms.service.RouterMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RouterMetricController {

    private final RouterMetricService service;

    @GetMapping("/getStatus")
    public Page<RouterMetric> getAllStatus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return service.getAllStatus(PageRequest.of(page, size));
    }

    @GetMapping("/getDeviceStatus/{deviceId}")
    public RouterMetric getDeviceStatus(
            @PathVariable String deviceId
    ) {

        return service.getLatestDeviceStatus(deviceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Latest status not found for device: " + deviceId
                ));
    }
}
