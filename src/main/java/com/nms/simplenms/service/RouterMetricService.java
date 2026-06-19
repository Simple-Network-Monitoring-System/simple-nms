package com.nms.simplenms.service;

import com.nms.routersimulator.model.RouterMetric;
import com.nms.simplenms.repository.RouterMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouterMetricService {

    private final RouterMetricRepository repository;

    public Page<RouterMetric> getAllStatus(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public Optional<RouterMetric> getLatestDeviceStatus(String deviceId) {

        return repository.findTopByDeviceIdOrderByTimestampDesc(deviceId);
    }
}
