package com.adidas.utility;

public class payload {

	public static String addPet(String petName, String petID, String status) {
		return "{\r\n" + "  \"id\": 0,\r\n" + "  \"category\": {\r\n" + "    \"id\":" + petID + ",\r\n"
				+ "    \"name\": \"" + petName + "\"\r\n" + "  },\r\n" + "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n" + "    \"string\"\r\n" + "  ],\r\n" + "  \"tags\": [\r\n" + "    {\r\n"
				+ "      \"id\": 0,\r\n" + "      \"name\": \"string\"\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"status\": \"" + status + "\"\r\n" + "}";

	}

	public static String updatePet(long updateID, String status) {
		return "{\r\n" + "  \"id\": " + updateID + ",\r\n" + "  \"status\": \"" + status + "\"\r\n" + "}";

	}
}
