package com.cms.app.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity 
@Table(name="pages")
@Data
public class Page {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Size(min=2,message="Title must be greater than 2 char")
	private String title;
	private String slug;
	@Size(min=5,message="Content must contain above 5 words")
	private String content;
	private int sorting;

}
