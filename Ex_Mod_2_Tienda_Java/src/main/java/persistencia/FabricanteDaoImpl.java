package persistencia;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Fabricante;
import persistencia.util.EMF;

public class FabricanteDaoImpl implements FabricanteDao {
	
	private EntityManager em;

	@Override
	public void save(Fabricante fabricante) {
		em = EMF.getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(fabricante);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Fabricante findByIdLazy(int idFabricante) {
		em = EMF.getEmf().createEntityManager();

		Fabricante fabricanteLazyById = em.find(Fabricante.class, idFabricante);
		
//		String ById = "select f from Fabricante f where f.idFabricante = :id";
//		
//		TypedQuery<Fabricante> qFab = em.createQuery(ById, Fabricante.class);
//		qFab.setParameter("id", idFabricante);
//		
//		Fabricante fabricanteLazyById = qFab.getSingleResult();
		em.close();
		return fabricanteLazyById;
	}

	@Override
	public Fabricante findById(int idFabricante) {
		em = EMF.getEmf().createEntityManager();
		String ById = "select f from Fabricante f left join fetch f.productos where f.idFabricante = :id";
		
		TypedQuery<Fabricante> qFab = em.createQuery(ById, Fabricante.class);
		qFab.setParameter("id", idFabricante);
		
		Fabricante fabricanteById = new Fabricante();
		try {
			fabricanteById = qFab.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		em.close();
		
		return fabricanteById;
	}

	@Override
	public Set<Fabricante> findOnlyActive() {
		em = EMF.getEmf().createEntityManager();
		
		String onlyId = "select f from Fabricante f join f.productos";
		
		TypedQuery<Fabricante> qFab = em.createQuery(onlyId, Fabricante.class);
		List<Fabricante> onlyActiveFabs = qFab.getResultList();
		
		Set<Fabricante> onlyActiveFabsSet = new TreeSet<Fabricante>(onlyActiveFabs);
		em.close();
		return onlyActiveFabsSet;
	}

	@Override
	public Set<Fabricante> findAll() {
		em = EMF.getEmf().createEntityManager();
		String all = "select f from Fabricante f ";
		
		TypedQuery<Fabricante> qAllFabs = em.createQuery(all, Fabricante.class);		
		List<Fabricante> farbricantes = qAllFabs.getResultList();
		
		Set<Fabricante> fabricantesSet = new TreeSet<Fabricante>(farbricantes);
		em.close();
		return fabricantesSet;
	}

}
