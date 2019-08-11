package com.easyjob.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O tamanho do título deve estar entre 1 e 50")
	@Size(min = 1, max = 50)
	private String tittle;

	@NotNull(message = "O tamanho da descrição deve estar entre 1 e 50")
	@Size(min = 5, max = 250)
	private String description;

	@NotNull(message = "O rating deve estar entre 0 e 5")
	@Min(0)
	@Max(5)
	private int rating;

	public Long getId() {
		return id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
