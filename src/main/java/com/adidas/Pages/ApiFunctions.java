package com.adidas.Pages;

import static io.restassured.RestAssured.given;
import java.util.List;

import com.adidas.base.BaseSetup;
import com.adidas.base.Constants;
import com.adidas.utility.FunctionsClass;
import com.adidas.utility.payload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ApiFunctions {
	FunctionsClass fc = new FunctionsClass();
	Boolean result;
	long petId;
	static long updatedPetId;
	RequestSpecification spec = new RequestSpecBuilder().setBaseUri(Constants.BASE_URL).build();

	@SuppressWarnings("rawtypes")
	public boolean getAvailablePets(String status) {
		int x = 0;
		result = false;
		String response = given().spec(spec).queryParam("status", status).header("Content-Type", "application/json")
				.when().get("/pet/findByStatus").then().assertThat().statusCode(200).extract().asString();
		JsonPath js = new JsonPath(response);
		List stausList = js.get("status");
		for (int i = 0; i < stausList.size(); i++) {
			if (!(stausList.get(i).toString().contains(status))) {
				x = x + 1;
			}
		}
		if (x == 0) {
			result = true;
			BaseSetup.test.get().info("All status are available");
		} else {
			BaseSetup.test.get().fail("All status are not available");
		}
		return result;
	}

	public boolean addPet(String name, String id, String status) {
		int x = 0;
		result = false;
		String response = given().spec(spec).auth().preemptive().basic("test", "abc123")
				.header("Content-Type", "application/json").body(payload.addPet(name, id, status)).when().post("pet")
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		petId = js.get("id");
		if (petId != 0) {
			BaseSetup.test.get().info("Pet is added: " + petId);
		} else {
			x = x + 1;
			BaseSetup.test.get().fail("Pet is not Added");
		}
		if (x == 0) {
			result = true;
		}
		return result;
	}

	public boolean updatePet(String status) {
		int x = 0;
		result = false;
		String response = given().spec(spec).auth().preemptive().basic("test", "abc123")
				.header("Content-Type", "application/json").body(payload.updatePet(petId, status)).when().put("pet")
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		String statusUpdated = js.get("status");
		updatedPetId = js.get("id");
		if (statusUpdated.contains(status)) {
			BaseSetup.test.get().info("Pet is updated: " + status);
		} else {
			x = x + 1;
			BaseSetup.test.get().fail("Pet is not Updated");
		}
		if (x == 0) {
			result = true;
		}
		return result;
	}
	
	public boolean deletePet() {
		int x = 0;
		result = false;
		String response = given().spec(spec).pathParam("petId", updatedPetId).auth().preemptive().basic("test", "abc123")
				.header("Content-Type", "application/json").when().delete("pet/{petId}")
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		long message = js.get("message");
		if (message == updatedPetId) {
			BaseSetup.test.get().info("Pet is deleted: " + message);
		} else {
			x = x + 1;
			BaseSetup.test.get().fail("Pet is not deleted");
		}
		if (x == 0) {
			result = true;
		}
		return result;
	}

}
