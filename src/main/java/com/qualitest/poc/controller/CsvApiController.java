package com.qualitest.poc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitest.poc.model.Items;
import com.qualitest.poc.model.Keys;
import com.qualitest.poc.model.Rules;
import com.qualitest.poc.util.CSVHelper;

@RestController
public class CsvApiController {

	@Autowired
	private ObjectMapper mapper;

	@PostMapping(path = "/runRuleEngine")
	public ResponseEntity<?> processRules(@RequestParam("items") MultipartFile itemsFile,
			@RequestParam("keys") MultipartFile keysFile, @RequestParam("rules") String rule) {

		List<Items> items = null;
		List<Keys> keys = null;
		Rules rul = null;

		if (CSVHelper.hasCSVFormat(itemsFile)) {
			try {
				items = CSVHelper.csvToItems(itemsFile.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Unable to read file->" + itemsFile.getOriginalFilename());
			}
		}

		if (CSVHelper.hasCSVFormat(keysFile)) {
			try {
				keys = CSVHelper.csvToKeys(keysFile.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Unable to read file->" + keysFile.getOriginalFilename());
			}
		}
		
		
		try {
			rul = mapper.readValue(rule, Rules.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Rules Json");
		}

		return new ResponseEntity<>(rul, HttpStatus.OK);
	}

}
