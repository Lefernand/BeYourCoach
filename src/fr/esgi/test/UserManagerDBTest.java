package fr.esgi.test;

import fr.esgi.servlets.*;
import fr.esgi.model.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserManagerDBTest {
	
	@Test
	public void testCheckLogin() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
		
		String login = "admin";
		
		assertEquals(true,usermanager.checkLogin(login));
		assertEquals(false,usermanager.checkLogin("logininconnu"));
	}
	
	@Test
	public void testCheckLoginWithPassword() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
		
		String login = "admin";
		String password = "admin";
		
		assertEquals(true,usermanager.checkLoginWithPassword(login, password));
		assertEquals(false,usermanager.checkLoginWithPassword(login, "passwordincorrect"));
	}
	
	@Test
	public void testGetUser() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
		
		String login = "admin";
		int taille = 180;
		
		assertEquals("admin@admin.fr",usermanager.getUser(login).getEmail());
		assertEquals("Administrateur",usermanager.getUser(login).getNom());
		assertEquals("Administrateur",usermanager.getUser(login).getPrenom());
		assertEquals("admin",usermanager.getUser(login).getRole());		
		assertTrue("Error test fail, mauvaise taille pour le user admin", usermanager.getUser(login).getTaille() == 180);
	}
	
	@Test
	public void testGetAllUser() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertTrue("Error test fail, mauvais retour de getUser", usermanager.allUsers().size() == 4);
		assertEquals("admin@admin.fr",usermanager.allUsers().get(0).getEmail());
		
	}
	
	@Test
	public void testEditProfile() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertEquals("mathias@gmail.com",usermanager.getUser("mathias").getEmail());
		usermanager.editProfile(13, "mathias", "mathias", "mathias", 180, 70, "1994-01-02", true, "mathias2@gmail.com", "mathias");
		assertEquals("mathias2@gmail.com",usermanager.getUser("mathias").getEmail());
		usermanager.editProfile(13, "mathias", "mathias", "mathias", 180, 70, "1994-01-02", true, "mathias@gmail.com", "mathias");
		assertEquals("mathias@gmail.com",usermanager.getUser("mathias").getEmail());
	}
	
	@Test
	public void testIsPoids() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertEquals("ok",usermanager.isPoidsDuJour(5));
		assertEquals("pasok",usermanager.isPoidsDuJour(6));
		
	}
	
	@Test
	public void testSetRoleAdmin() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertEquals("user",usermanager.getUser("user").getRole());
		usermanager.setRoleAdmin(6);
		assertEquals("admin",usermanager.getUser("user").getRole());
		usermanager.setRoleUser(6);		
	}
	
	@Test
	public void testAjoutPoids() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertEquals(true,usermanager.setPoids(5, 666, 180, java.sql.Date.valueOf(java.time.LocalDate.now()), true, java.sql.Date.valueOf(java.time.LocalDate.now())));	
	}
	
	@Test
	public void testDeletePoids() throws Exception {
		
		UserManagerDB usermanager = new UserManagerDB();
				
		assertEquals(true,usermanager.deletePoids2(22));
	}
	
	

}
