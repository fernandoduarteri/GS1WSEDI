package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderItems database table.
 * 
 */
@Entity
@Table(name="orderItems")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idOrderItem;
	
	private String cantidad;

	private String codigo;

	private String descuentoMonto;

	private String descuentoPorciento;

	private String despachado;

	private String glnCliente;

	private String ivaMonto;

	private String ivaPorciento;

	private String montoMonetario;

	private String numeroOrden;

	private String precio;

	public OrderItem() {
	}

	public String getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescuentoMonto() {
		return this.descuentoMonto;
	}

	public void setDescuentoMonto(String descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}

	public String getDescuentoPorciento() {
		return this.descuentoPorciento;
	}

	public void setDescuentoPorciento(String descuentoPorciento) {
		this.descuentoPorciento = descuentoPorciento;
	}

	public String getDespachado() {
		return this.despachado;
	}

	public void setDespachado(String despachado) {
		this.despachado = despachado;
	}

	public String getGlnCliente() {
		return this.glnCliente;
	}

	public void setGlnCliente(String glnCliente) {
		this.glnCliente = glnCliente;
	}

	public String getIvaMonto() {
		return this.ivaMonto;
	}

	public void setIvaMonto(String ivaMonto) {
		this.ivaMonto = ivaMonto;
	}

	public String getIvaPorciento() {
		return this.ivaPorciento;
	}

	public void setIvaPorciento(String ivaPorciento) {
		this.ivaPorciento = ivaPorciento;
	}

	public String getMontoMonetario() {
		return this.montoMonetario;
	}

	public void setMontoMonetario(String montoMonetario) {
		this.montoMonetario = montoMonetario;
	}

	public String getNumeroOrden() {
		return this.numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getPrecio() {
		return this.precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

}