package com.logging.presentation.main.service.scheduled;

import com.logging.presentation.logging.starter.cross.identifier.AddCrossIdentifier;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JobService {

    @AddCrossIdentifier(initiator = "scheduler", objectFieldName = "job", identifierFieldName = "claimId")
    public void doJob(Job job) {
        log.info("Выполнение запланированной задачи: {}: {}", job.getClaimId(), job.getText());
    }

    @Value
    public static class Job {
        UUID claimId;
        String text;
    }
}
