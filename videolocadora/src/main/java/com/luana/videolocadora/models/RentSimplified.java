package com.luana.videolocadora.models;

import java.util.Date;

public class RentSimplified {
	private Integer idMovie;
	private String movieTitle;
	private String username;
	private Date rentalDate;
	private Date returnDate;
	
	public RentSimplified(Integer idMovie, String movieTitle, String username, Date rentalDate,
			Date returnDate) {
		super();
		this.idMovie = idMovie;
		this.movieTitle = movieTitle;
		this.username = username;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public Integer getIdMovie() {
		return idMovie;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getUsername() {
		return username;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}		
}
