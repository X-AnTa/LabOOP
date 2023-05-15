package com.anta.service;

import com.anta.dto.CarDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabOOPService {

	private final static String path = "/home/anatoliy/IdeaProjects/LabOOP/src/main/resources/templates/output.txt";

	public static String readFileAsString(String filePath) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	public void fillCarsTxt(List<CarDTO> carList) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (CarDTO car : carList) {
				bufferedWriter.write(car.toString());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
			System.out.println("Список успешно записан в файл.");
		} catch (IOException e) {
			System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
		}
	}

	private List<CarDTO> getCarsFromTxt() {
		List<CarDTO> carList = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				CarDTO car = parseCar(line);
				carList.add(car);
			}

			bufferedReader.close();
			System.out.println("Данные успешно считаны из файла.");
		} catch (IOException e) {
			System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
		}
		return carList;
	}

	private static CarDTO parseCar(String line) {
		String[] fields = line.split(", ");

		String brand = fields[0].substring(fields[0].indexOf('=') + 1);
		String model = fields[1].substring(fields[1].indexOf('=') + 1);
		String year = fields[2].substring(fields[2].indexOf('=') + 1);
		String color = fields[3].substring(fields[3].indexOf('=') + 1);
		String bodyType = fields[4].substring(fields[4].indexOf('=') + 1, fields[4].indexOf(')'));

		return new CarDTO(brand, model, year, color, bodyType);
	}

	public String getJsonFromDTO() {
		List<CarDTO> cars = getCarsFromTxt();
		Gson gson = new Gson();

		return gson.toJson(cars);
	}
}
