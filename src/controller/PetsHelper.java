package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Pets;

public class PetsHelper {

	static EntityManagerFactory emfactory= Persistence.createEntityManagerFactory("Pets");
	
	public void insertPet(Pets p) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Pets> showAllPets(){
		EntityManager em = emfactory.createEntityManager();
		List<Pets> allPets = em.createQuery("SELECT p FROM Pets p").getResultList();
		return allPets;
	}
	
	public void deletePet(Pets toDelete) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery(
				"select p from Pets p where p.name = :selectedName and p.type = :selectedType and p.owner = :selectedOwner",
				Pets.class);
		
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		Pets result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}

	public Pets searchForPetById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Pets found = em.find(Pets.class, idToEdit);
		em.close();
		return found;
	}

	public void updatePet(Pets toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Pets> searchForPetByName(String nameName) {
		EntityManager em=emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery= em.createQuery("select p from Pets p where p.name = :selectedName", Pets.class);
		typedQuery.setParameter("selectedName", nameName);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}

	public List<Pets> searchForPetByType(String typeName) {
		// TODO Auto-generated method stub
		EntityManager em=emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery= em.createQuery("select p from Pets p where p.type = :selectedType", Pets.class);
		typedQuery.setParameter("selectedType", typeName);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}

	public List<Pets> searchForPetByOwner(String ownerName) {
		// TODO Auto-generated method stub
		EntityManager em=emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery= em.createQuery("select p from Pets p where p.owner = :selectedOwner", Pets.class);
		typedQuery.setParameter("selectedOwner", ownerName);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	
	
	public void cleanUp() {
		emfactory.close();
	}

	
}