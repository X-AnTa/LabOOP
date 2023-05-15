package com.anta.controller;

import com.anta.dto.CarDTO;
import com.anta.service.LabOOPService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.anta.service.LabOOPService.readFileAsString;

@Controller
@RequiredArgsConstructor
public class LabOOPController {

	private final LabOOPService labOOPService;
	private final ObjectMapper objectMapper;

	private final static String path = "/home/anatoliy/IdeaProjects/LabOOP/src/main/resources/templates/car-list.json";

	@GetMapping("/card")
	public String getCard() {
		return "card";
	}

	@GetMapping("/cars")
	public String getCarsList(Model model) {
		model.addAttribute("json", readFileAsString(path));
		return "car-list";
	}

	@GetMapping("/cars/add")
	public String getCarAdding(Model model) {
		model.addAttribute("json", readFileAsString(path));
		return "add-car";
	}

	@PostMapping("/cars/add")
	public String addCarJson(@RequestBody JsonNode js) throws IOException {
		objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, false).writeValue(new File(path), js);
		return "car-list";
	}

	@PostMapping("/cars/add-pojo")
	public String addCarPOJO(@RequestBody List<CarDTO> carDTO) {
		System.out.println(carDTO);
		labOOPService.fillCarsTxt(carDTO);
		return "car-list-pojo";
	}

	@GetMapping("/cars/add-pojo")
	public String getCarAddingPOJO(Model model) {
		model.addAttribute("json", labOOPService.getJsonFromDTO());
		return "add-car-pojo";
	}

	@GetMapping("/cars-pojo")
	public String getCarsListPOJO(Model model) {
		model.addAttribute("json", labOOPService.getJsonFromDTO());
		return "car-list-pojo";
	}
}
