package fr.esgi.test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import fr.esgi.model.Aliment;
import fr.esgi.model.UserManagerDB;
import fr.esgi.model.alimentManagerDB;

public class alimentManagerDBTest {

	@Test
	public void testConnexion() throws Exception {
		
		alimentManagerDB alimentManager = new alimentManagerDB();
		
		assertNotNull(alimentManager.getConnection());
	}
	
	@Test
	public void testAjoutRepas() throws Exception {
		
		alimentManagerDB alimentManager = new alimentManagerDB();
		
		Aliment alimment1 = new Aliment("alimnet1", "alimnet1", "alimnet1", java.sql.Date.valueOf(java.time.LocalDate.now()), 33, 33, 13);
		Aliment alimment2 = new Aliment("alimnet1", "alimnet1", "alimnet1", java.sql.Date.valueOf(java.time.LocalDate.now()), 33, 33, 13);
		Aliment alimment3 = new Aliment("alimnet1", "alimnet1", "alimnet1", java.sql.Date.valueOf(java.time.LocalDate.now()), 33, 33, 13);
		
		ArrayList<Aliment> list = new ArrayList<Aliment>();
		
		list.add(alimment1);
		list.add(alimment2);
		list.add(alimment3);
		
		assertNotNull(alimentManager.ajoutRepas(list));
	}
}
