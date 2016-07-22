package fr.esgi.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esgi.model.UserManagerDB;
import fr.esgi.model.alimentManagerDB;

public class alimentManagerDBTest {

	@Test
	public void testConnexion() throws Exception {
		
		alimentManagerDB alimentManager = new alimentManagerDB();
		
		assertNotNull(alimentManager.getConnection());
	}
	
	@Test
	public void testConnexion() throws Exception {
		
		alimentManagerDB alimentManager = new alimentManagerDB();
		
		assertNotNull(alimentManager.getConnection());
	}
}
