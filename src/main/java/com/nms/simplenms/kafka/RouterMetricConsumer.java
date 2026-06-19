package com.nms.simplenms.kafka;



import com.nms.routersimulator.model.RouterMetric;
import com.nms.simplenms.repository.RouterMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouterMetricConsumer {

    private final RouterMetricRepository repository;

    @KafkaListener(
            topics = "router-metrics",
            groupId = "simple-nms-group"
    )
    public void consume(RouterMetric metric) {

        repository.save(metric);

        System.out.println("SAVED TO DB : " + metric);
    }

}
