package io.anand.springboot;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="/info", description="Operations for Information")
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
