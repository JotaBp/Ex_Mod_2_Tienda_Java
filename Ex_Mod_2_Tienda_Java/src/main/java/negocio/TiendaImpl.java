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
				if (o1.equals(o2))
					return 0;
				String prod1 = o1.getNombreProd() + o1.getIdProducto();
				String prod2 = o2.getNombreProd() + o2.getIdProducto();
				return Collator.getInstance(new Locale("es")).compare(prod1, prod2);
			}

		});
		nameFound.addAll(daoProd.findAll());
		return nameFound;
	}

	@Override
	public Set<Producto> getProductos(String descripcion) {
//		Set<Producto> prodFound = new TreeSet<Producto>();
//		prodFound.addAll(daoProd.findByDescripcion(descripcion));
//		if(prodFound.size() == 0) return null;
//		return prodFound;
		List<Producto> prodFound = daoProd.findByDescripcion(descripcion);
		return prodFound.size() > 0 ? new TreeSet<Producto>(prodFound) : null;
	}

	@Override
	public double getMediaPrecioProductosByFabricante(int idFabricante) {
		Fabricante fabricante = daoFab.findById(idFabricante);
		Double media = 0.0;

		if (fabricante != null) {
			List<Producto> productos = fabricante.getProductos();
			if (productos != null && productos.size() > 0) {
				for (Producto producto : productos) {
					media += producto.getPrecio();
				}
				media /= productos.size();
			}
		}

		return media;
	}

	@Override
	public void addFabricante(Fabricante fabricante) {
		daoFab.save(fabricante);
	}

	@Override
	public void addProducto(Producto producto) {
		daoProd.save(producto);
	}

	@Override
	public Set<Fabricante> getFabricantes() {
		Set<Fabricante> fabsOrdenadosSet = new TreeSet<Fabricante>(new Comparator<Fabricante>() {
			@Override
			public int compare(Fabricante o1, Fabricante o2) {
				if (o1.equals(o2))
					return 0;
				String fabO1 = o1.getNombreFab() + o1.getIdFabricante();
				String fabO2 = o2.getNombreFab() + o2.getIdFabricante();
				return Collator.getInstance(new Locale("es")).compare(fabO1, fabO2);
			}
		});
		fabsOrdenadosSet.addAll(daoFab.findAll());
		return fabsOrdenadosSet;
	}

	@Override
	public Set<Fabricante> getFabricantesActivos() {
		Set<Fabricante> fabsOrdenadosSet = new TreeSet<Fabricante>(new Comparator<Fabricante>() {
			@Override
			public int compare(Fabricante o1, Fabricante o2) {
				if (o1.equals(o2))
					return 0;
				String fabO1 = o1.getNombreFab() + o1.getIdFabricante();
				String fabO2 = o2.getNombreFab() + o2.getIdFabricante();
				return Collator.getInstance(new Locale("es")).compare(fabO1, fabO2);
			}
		});
		fabsOrdenadosSet.addAll(daoFab.findOnlyActive());
		return fabsOrdenadosSet;
	}

	@Override
	public Fabricante getFabricante(int idFabricante) {
		return daoFab.findByIdLazy(idFabricante);
	}

	@Override
	public Fabricante getFabricanteConProductos(int idFabricante) {
		// TODO Auto-generated method stub
		return null;
	}

}
