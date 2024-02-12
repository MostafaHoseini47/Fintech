package com.snapp.fintech.config.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiMetrics {

    private final Counter apiCallCounter;

    @Autowired
    public ApiMetrics(MeterRegistry meterRegistry) {
        this.apiCallCounter = Counter.builder("api.calls.total")
                .description("Total number of calls to the API endpoint")
                .register(meterRegistry);
    }

    public void incrementApiCallCounter() {
        apiCallCounter.increment();
    }
}
