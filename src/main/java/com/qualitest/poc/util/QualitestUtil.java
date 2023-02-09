package com.qualitest.poc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.qualitest.poc.model.CsvRule;
import com.qualitest.poc.model.User;

public interface QualitestUtil {

	public static String writeUserIntoFile(User user) throws Exception {
		List<User> usersList = readUsers();
		if (!ObjectUtils.isEmpty(usersList)) {
			if (usersList.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))) {
				throw new Exception("User Already exist with userName : " + user.getUsername());
			}
		}
		usersList.add(user);
		return writeUser(usersList);
	}

	public static List<User> readUsers() {

		List<User> t = new ArrayList<>();
		File file = new File("src/main/resources/users.json");

		try {
			ObjectMapper mapper = new ObjectMapper();

			CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);

			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			t = mapper.readValue(file, listType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	public static String writeUser(List<User> classType) {

		File file = new File("src/main/resources/users.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.writeValue(file, classType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "UserSaved Successfully into Db";
	}

	public static List<CsvRule> getRulesFromJson() throws Exception {
		List<CsvRule> t = new ArrayList<>();
		File file = new File("src/main/resources/rules.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, CsvRule.class);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			t = mapper.readValue(file, listType);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return t;
	}

}
