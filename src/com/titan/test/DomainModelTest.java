package com.titan.test;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.titan.domain.Cabin;

public class DomainModelTest {

	private EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TitanUnit");
		entityManager = emf.createEntityManager();
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Cabin cabin = new Cabin();
		cabin.setShipId(1);
		cabin.setDeckLevel(2);
		cabin.setName("Master Suite");
		cabin.setBedCount(3);
		
		entityManager.persist(cabin);
		transaction.commit();		
		
		entityManager.clear();
		
		Cabin cabin2 = entityManager.find(Cabin.class, cabin.getId());
		Assert.assertEquals("Persistent entity != original entity",cabin, cabin2);
		
		transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(cabin2);
		transaction.commit();
	}

}
