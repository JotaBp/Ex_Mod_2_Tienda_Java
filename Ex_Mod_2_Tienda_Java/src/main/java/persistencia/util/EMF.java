package persistencia.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	
private static EntityManagerFactory emf;
	
	private EMF() {}
	
	public static EntityManagerFactory getEmf() {
		
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("tienda");
		}
		
		return emf;
	}
}
