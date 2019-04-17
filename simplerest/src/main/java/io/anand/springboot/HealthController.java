package io.anand.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthController {

	@Autowired
	private HealthService healthService;
	
	@RequestMapping("/health")
	public String getHealth () {
		return healthService.getHealth();
	}
	
	@RequestMapping("/health/version")
	public String getVersion () {
		return healthService.getVersion();
	}

	@RequestMapping("/health/build")
	public String getBuild () {
		return healthService.getBuild();
	}
}
