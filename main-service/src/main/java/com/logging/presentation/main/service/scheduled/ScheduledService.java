package com.logging.presentation.main.service.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduledService {

    private final Map<UUID, String> jobs = new ConcurrentHashMap<>();

    private final JobService jobService;

    public void addJob(UUID executionId, String text) {
        jobs.put(executionId, text);
    }

    @Scheduled(cron = "*/20 * * * * *")
    public void doJobs() {
        List<UUID> claimsToRemove = new ArrayList<>();
        for (Map.Entry<UUID, String> job : jobs.entrySet()) {
            jobService.doJob(new JobService.Job(job.getKey(), job.getValue()));
            claimsToRemove.add(job.getKey());
        }
        for (UUID claimToRemove : claimsToRemove) {
            jobs.remove(claimToRemove);
        }
    }
}
