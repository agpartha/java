package io.anand.springboot;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

	private CompositeMeterRegistry compositeRegistry = new CompositeMeterRegistry();
	@Autowired
	private PrometheusMeterRegistry promRegistry;

	public MetricsService () {
		compositeRegistry.add(promRegistry);
	}

	public Counter getCounter (String counterName, String tagName, String tagValue) {
		 return Counter.builder(counterName)
				 				.tag(tagName, tagValue)
				 				.register(compositeRegistry);
	}
}
