package com.logging.presentation.main.service.scheduled;

import com.logging.presentation.logging.starter.cross.identifier.AddIdentifiers;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JobService {

    @AddIdentifiers(initiator = "scheduler", objectFieldName = "job")
    public void doJob(Job job) {
        log.info("Выполнение запланированной задачи: {}: {}", job.getClaimId(), job.getText());
    }

    @Value
    public static class Job {
        UUID claimId;
        String text;
    }
}
