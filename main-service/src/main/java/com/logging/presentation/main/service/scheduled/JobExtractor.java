package com.logging.presentation.main.service.scheduled;

import com.logging.presentation.main.service.scheduled.JobService.Job;
import io.github.shurupov.logstamp.extractor.StampExtractor;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JobExtractor implements StampExtractor<Job> {

  @Override
  public boolean canExtract(Object container) {
    return container instanceof Job;
  }

  @Override
  public Map<String, String> typedExtract(Job job) {
    return Map.of("claimId", job.getClaimId().toString());
  }
}
