package negocio;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import modelo.Fabricante;
import modelo.Producto;
import persistencia.FabricanteDao;
import persistencia.FabricanteDaoImpl;
import persistencia.ProductoDao;
import persistencia.ProductoDaoImpl;

public class TiendaImpl implements Tienda {

	private ProductoDao daoProd;
	private FabricanteDao daoFab;

	

	public TiendaImpl() {
		this.daoProd = new ProductoDaoImpl();
		this.daoFab = new FabricanteDaoImpl();
	}

	@Override
	public Set<Producto> getProductos() {
		Set<Producto> nameFound = new TreeSet<Producto>(new Comparator<Producto>() {
			@Override
			public int compare(Producto o1, Producto o2) {
				if (o1.equals(o2))	return 0;
				String prod1 = o1.getNombreProd() + o1.getIdProducto();
				String prod2 = o2.getNombreProd() + o2.getIdProducto();
				return Collator.getInstance(new Locale("es")).compare(prod1,prod2);
				}
			
		});
		nameFound.addAll(daoProd.findAll());
		return nameFound;
	}

	@Override
	public Set<Producto> getProductos(String descripcion) {
		Set<Producto> prodFound = new TreeSet<Producto>();
		prodFound.addAll(daoProd.findByDescripcion(descripcion));
		return prodFound;
	}

	@Override
	public double getMediaPrecioProductosByFabricante(int idFabricante) {
		List<Producto> fabricantes = daoFab.findById(idFabricante).getProductos();
		for (Producto producto : fabricantes) {
			producto.getPrecio();
		} 
		return 0;
	}

	@Override
	public void addFabricante(Fabricante fabricante) {
		daoFab.save(fabricante);
	}

	@Override
	public void addProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Fabricante> getFabricantes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Fabricante> getFabricantesActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fabricante getFabricante(int idFabricante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fabricante getFabricanteConProductos(int idFabricante) {
		// TODO Auto-generated method stub
		return null;
	}

}
