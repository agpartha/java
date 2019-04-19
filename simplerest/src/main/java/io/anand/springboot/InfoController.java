package io.anand.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoController {

	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/info")
	public String getHealth () {
		return infoService.getHealth();
	}
	
	@RequestMapping("/info/version")
	public String getVersion () {
		return infoService.getVersion();
	}

	@RequestMapping("/info/build")
	public String getBuild () {
		return infoService.getBuild();
	}
}
