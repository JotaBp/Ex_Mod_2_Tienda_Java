package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.Producto;
import persistencia.util.EMF;

public class ProductoDaoImpl implements ProductoDao{
	
	private EntityManager em;

	@Override
	public Producto findById(int idProducto) {
		em = EMF.getEmf().createEntityManager();
		
		Producto productoById = em.find(Producto.class, idProducto);
//		String byId = "select p from Producto p where p.idProducto = :id";
//		
//		TypedQuery<Producto> qProd = em.createQuery(byId, Producto.class);
//		qProd.setParameter("id", idProducto);
//	
//		Producto productoById = qProd.getSingleResult();
		em.close();
		return productoById;
	}

	@Override
	public List<Producto> findByDescripcion(String descripcion) {
		em = EMF.getEmf().createEntityManager();
		
		String byDescripcion = "select p from Producto p where p.nombreProd like :nombre";
		TypedQuery<Producto> qDescProd = em.createQuery(byDescripcion, Producto.class);
		qDescProd.setParameter("nombre", "%" + descripcion + "%");
		
		List<Producto> productos = qDescProd.getResultList();
		
		em.close();
		
		return productos;
	}

	@Override
	public List<Producto> findAll() {
		em = EMF.getEmf().createEntityManager();
		
		String all = "select p from Producto p";
		TypedQuery<Producto> qAllProd = em.createQuery(all, Producto.class);
		
		List<Producto> allProductos = qAllProd.getResultList();
		
		em.close();
		
		return allProductos;
	}

	@Override
	public void save(Producto p) {
		em = EMF.getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		
	}

}
