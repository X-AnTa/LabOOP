package com.anta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDTO {
	private String brand;
	private String model;
	private String year;
	private String color;
	private String bodyType;
}
