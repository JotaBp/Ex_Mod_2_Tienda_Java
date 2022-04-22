package modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fabricantes")
public class Fabricante implements Serializable, Comparable<Fabricante> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fabricante")
	private Integer idFabricante;
	@Column(name = "fabricante")
	private String nombreFab;
	@OneToMany(mappedBy = "fabricanteProd")
	private List<Producto> productos;
	
	public Integer getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}
	public String getNombreFab() {
		return nombreFab;
	}
	public void setNombreFab(String nombreFab) {
		this.nombreFab = nombreFab;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFabricante);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		return Objects.equals(idFabricante, other.idFabricante);
	}
	@Override
	public int compareTo(Fabricante o) {
		return this.idFabricante - o.idFabricante;
	}
	
}
