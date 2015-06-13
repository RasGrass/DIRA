package com.rasgrass.dira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author RasGrass
 */
@Entity
@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Position implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Must not be null")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Must not be null")
	private Integer minimumWage;

	@Column(nullable = false)
	@NotBlank(message = "Must not be null")
	private Integer maximumWage;

}
