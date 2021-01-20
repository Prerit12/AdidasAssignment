package com.adidas.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adidas.Pages.ApiFunctions;
import com.adidas.base.BaseSetup;

public class APITest extends BaseSetup {
	

	@Test(priority = 1)
	public void getAvailablePets() {
		try {
			ApiFunctions af = new ApiFunctions();
			Assert.assertTrue(af.getAvailablePets("available"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	@Test(priority = 2)
	public void addPet() {
		try {
			ApiFunctions af = new ApiFunctions();
			Assert.assertTrue(af.addPet("Dogs1","12","available"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	@Test(priority = 3)
	public void updatePetStatus() {
		try {
			ApiFunctions af = new ApiFunctions();
			Assert.assertTrue(af.updatePet("sold"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	@Test(priority = 4)
	public void deletePet() {
		try {
			ApiFunctions af = new ApiFunctions();
			Assert.assertTrue(af.deletePet());
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

}
