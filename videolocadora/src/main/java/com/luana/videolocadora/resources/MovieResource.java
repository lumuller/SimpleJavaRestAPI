package com.luana.videolocadora.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luana.videolocadora.models.Movie;
import com.luana.videolocadora.service.MovieService;


@RestController
@RequestMapping(value="/www.videolocadora.com")
public class MovieResource {
	
	@Autowired
	MovieService movieService;
	
	//Métodos para CRUD de Filmes
	@Secured("ROLE_ADMIN")
	@PostMapping("/movies")
	public Movie saveMovies(@RequestBody Movie movie) {
		return movieService.saveMovies(movie);
	}	
	
	//DEVEM SER FILMES DISPONIVEIS
	@GetMapping("/movies")
	public List<Movie> listMovies(){	
		return movieService.listMovies(); 
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/movies/{movieId}")
	public String deleteFilme(@PathVariable(value="movieId") Integer movieId)  {
		return movieService.deleteFilme(movieId);	
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/movies")
	public Movie updateMovie(@RequestBody Movie movie) {
		return movieService.updateMovie(movie);
	}
}
