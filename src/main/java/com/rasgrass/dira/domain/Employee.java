package com.rasgrass.dira.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@Entity
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@ManyToOne(optional = false)
	private Position position;

}
