package com.luana.videolocadora.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luana.videolocadora.models.RentSimplified;
import com.luana.videolocadora.service.RentService;


@RestController
@RequestMapping(value="/www.videolocadora.com")
public class RentResource {
	@Autowired
	RentService rentService;
	
	@PostMapping("/rents/{movieId}")
	public String rentMovie(@PathVariable(value="movieId") Integer movieId) {						
		return rentService.rentMovie(movieId);
	}	
	
		
	@PutMapping("/rents/{movieId}")
	public String returnMovie(@PathVariable(value="movieId") Integer movieId) {							
		return rentService.returnMovie(movieId);		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/rents")
	public List<RentSimplified> listNotReturnedRents(){	
		return rentService.listNotReturnedRents();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/rents/{movieId}")
	public List<RentSimplified> listRentsFromMovie(@PathVariable(value="movieId") Integer movieId){		
		return rentService.listRentsFromMovie(movieId);
	}	
}
