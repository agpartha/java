package io.anand.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.area1.devices.PanCommand;
import com.area1.devices.PanCommand.Action;
import com.area1.devices.PanCommand.Subject;

@SpringBootApplication
public class RestApplication {
	
	public static void main (String [] args) {
	    
	    String SYSTEM_QUERY = "cmd=<show><system><info></info></system></show>";
	    String body="cmd=<show><system><info></info></system></show>&key=";
	    
        if (body.contains(SYSTEM_QUERY))
            System.out.println("Found System Information command");
	    SpringApplication.run(RestApplication.class, args);
	}

}
