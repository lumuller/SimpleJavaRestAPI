package com.luana.videolocadora.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.luana.videolocadora.models.Movie;
import com.luana.videolocadora.models.Rent;
import com.luana.videolocadora.models.RentSimplified;
import com.luana.videolocadora.models.User;
import com.luana.videolocadora.repository.MoviesRepository;
import com.luana.videolocadora.repository.RentsRepository;
import com.luana.videolocadora.repository.UsersRepository;

@Service
public class RentService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	MoviesRepository moviesRepository;
	
	@Autowired
	RentsRepository rentsRepository;
	

	public String rentMovie(Integer movieId) {						
		Rent newRentRegister = new Rent();		
		Optional<Movie> requiredMovie = moviesRepository.findById(movieId);
		if(requiredMovie.isPresent()) {
			if(requiredMovie.get().isAvailable()) {
				requiredMovie.get().setAvailable(false);
				newRentRegister.setMovie(requiredMovie.get());				
				User user = usersRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
				newRentRegister.setUser(user);				
				newRentRegister.setRentalDate(new Date());			
				rentsRepository.save(newRentRegister);				
				return String.format("Locação do filme %s (id %d) efetuada com sucesso por %s.", requiredMovie.get().getTitle(), requiredMovie.get().getId(),user.getName());
			} else {				
				return String.format("O filme %s (id %d) não está disponível para locação.", requiredMovie.get().getTitle(), requiredMovie.get().getId());
			}
		} else {
			return String.format("Não foi possível identificar um filme com o id %d.", movieId);
		}
	}	
	
		
	public String returnMovie(Integer movieId) {							
				
		Optional<Movie> requiredMovie = moviesRepository.findById(movieId);
		if(requiredMovie.isPresent()) {			
			User user = usersRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());			
			List<Rent> movieRents = rentsRepository.findAllByMovie(requiredMovie.get());			
			List<Rent> movieRentsPerformedByCurrentUser = movieRents.stream()  
		                .filter(str -> str.getUser().getUsername().equals(user.getUsername()) && str.getReturnDate() == null) 
		                .collect(Collectors.toList());  			
			if(movieRentsPerformedByCurrentUser.isEmpty()) {
				return String.format("Não foi possível identificar um registro de locação do filme %s em aberto para seu usuário (%s). ", movieId, user.getUsername());
			} else {
				Rent rentalRegister = movieRentsPerformedByCurrentUser.iterator().next();				
				rentalRegister.setReturnDate(new Date());
				rentalRegister.getMovie().setAvailable(true);
				moviesRepository.save(rentalRegister.getMovie());
				rentsRepository.save(rentalRegister);				
				return String.format("%s, o filme %s (id %d) foi devolvido com sucesso.", user.getName(), requiredMovie.get().getTitle(), requiredMovie.get().getId());
			}			
		} else {
			return String.format("Não foi possível identificar um filme com o id %d.", movieId);
		}
	}
	

	public List<RentSimplified> listNotReturnedRents(){	
		List<Rent> rentsNotReturned = rentsRepository.findAll().stream().filter(l -> l.getReturnDate() == null).collect(Collectors.toList()); 
		List<RentSimplified> presentationList = new ArrayList<>();
		for(Rent l : rentsNotReturned){
			presentationList.add(new RentSimplified(l.getMovie().getId(), l.getMovie().getTitle(), l.getUser().getUsername(), 
					l.getRentalDate(), l.getReturnDate()));
		}
		return presentationList;
	}
	

	public List<RentSimplified> listRentsFromMovie(Integer movieId){		
		Optional<Movie> requiredMovie = moviesRepository.findById(movieId);
		if(requiredMovie.isPresent()) {		
			List<Rent> rentsFromMovie = rentsRepository.findAllByMovie(requiredMovie.get());			
			List<RentSimplified> presentationList = new ArrayList<>();
			for(Rent l : rentsFromMovie){
				presentationList.add(new RentSimplified(l.getMovie().getId(), l.getMovie().getTitle(), l.getUser().getUsername(), 
						l.getRentalDate(), l.getReturnDate()));
			}			
			presentationList.sort((RentSimplified l1, RentSimplified l2)->l2.getRentalDate().compareTo(l1.getRentalDate()));										
			return presentationList;	
		}		
		return null;
	}	
}
