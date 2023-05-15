# LabOOP
Лабораторная работа 1
  Сверстать сайты визитки с использованием Bootstrap
  Формат страниц:
- Услуги(Главная),
- Контакты,
- О нас,
- Обратная связь.
  Вызов:
  @GetMapping("/card")
	  public String getCard() {
	  	return "card";
	  }
------------------------    
Лабораторная работа 2
  Разработать страницу отображающую список автомобилей,
  с хранением их на сервере в файле, передача данных осуществляется по JSON, 
  клиент и сервер общаются по средством передачи данных. 
  На странице предусмотреть форму добавления объектов в список.
  Вызовы:
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
------------------------    
Лабораторная работа 3
  Разработать страницу, отображающую список автомобилей,
  с хранением их на сервере файле. передача данных осуществляется по JSON, 
  клиент и сервер общаются по средством передачи данных. Применить шаблон проектирования MVC. 
  На странице предусмотреть форму добавления объектов в список.
  Вызовы:
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
