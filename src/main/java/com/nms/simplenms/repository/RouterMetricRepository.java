package com.nms.simplenms.repository;

import com.nms.routersimulator.model.RouterMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouterMetricRepository extends JpaRepository<RouterMetric, Long> {

    Optional<RouterMetric> findTopByDeviceIdOrderByTimestampDesc(String deviceId);

}
