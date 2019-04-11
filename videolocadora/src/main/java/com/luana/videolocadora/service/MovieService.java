package com.luana.videolocadora.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luana.videolocadora.models.Movie;
import com.luana.videolocadora.repository.MoviesRepository;

@Service
public class MovieService {
	
	@Autowired
	MoviesRepository moviesRepository;
	
	public Movie saveMovies(Movie movie) {
		return moviesRepository.save(movie);
	}	
	
	public List<Movie> listMovies(){	
		return moviesRepository.findAll().stream().filter(f -> f.isAvailable() == true).collect(Collectors.toList()); 
	}
	
	public String deleteFilme(Integer movieId)  {
		try {
			moviesRepository.deleteById(movieId);		
			return String.format("Movie %d was removed successfully.", movieId);
		}catch(Exception e) {
			return String.format("A error happened while removing the movie. Error information: %s", e.getMessage());
		}
	}

	public Movie updateMovie(Movie movie) {
		return moviesRepository.save(movie);
	}
}
