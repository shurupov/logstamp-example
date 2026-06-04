package com.logging.presentation.main.service.scheduled;

import io.github.shurupov.logstamp.aspect.AddStamps;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JobService {

    @AddStamps(initiator = "scheduler")
    public void doJob(Job job) {
        log.info("Выполнение запланированной задачи: {}: {}", job.getExecutionId(), job.getText());
    }

    @Value
    public static class Job {
        UUID executionId;
        String text;
    }
}
