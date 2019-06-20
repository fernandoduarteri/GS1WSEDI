package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String clienteCedula;

	private String clienteContactoDepto;

	private String clienteContactoEmpleado;

	private String clienteContactoTelefono;

	private String clienteNombre;

	private String estado;

	private Timestamp fechaEntrega;

	private Timestamp fechaIntercambio;

	private Timestamp fechaOrden;

	private String glnProveedor;

	private int id;

	private String lugarEntregaCalle;

	private String lugarEntregaCedula;

	private String lugarEntregaCiudad;

	private String lugarEntregaGlnCliente;

	private String lugarEntregaNombre;

	private String lugarEntregaTelefono;

	private String proveedorCedula;

	private String proveedorContactoDepto;

	private String proveedorContactoEmpleado;

	private String proveedorContactoTelefono;

	private String proveedorNombre;

	private String seqIntercambio;

	private String total;

	private String totalDescuento;

	private String totalItems;

	private String totalIva;

	private String totalPaquetes;

	public Order() {
	}

	
	public String getClienteCedula() {
		return this.clienteCedula;
	}

	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}

	public String getClienteContactoDepto() {
		return this.clienteContactoDepto;
	}

	public void setClienteContactoDepto(String clienteContactoDepto) {
		this.clienteContactoDepto = clienteContactoDepto;
	}

	public String getClienteContactoEmpleado() {
		return this.clienteContactoEmpleado;
	}

	public void setClienteContactoEmpleado(String clienteContactoEmpleado) {
		this.clienteContactoEmpleado = clienteContactoEmpleado;
	}

	public String getClienteContactoTelefono() {
		return this.clienteContactoTelefono;
	}

	public void setClienteContactoTelefono(String clienteContactoTelefono) {
		this.clienteContactoTelefono = clienteContactoTelefono;
	}

	public String getClienteNombre() {
		return this.clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Timestamp getFechaIntercambio() {
		return this.fechaIntercambio;
	}

	public void setFechaIntercambio(Timestamp fechaIntercambio) {
		this.fechaIntercambio = fechaIntercambio;
	}

	public Timestamp getFechaOrden() {
		return this.fechaOrden;
	}

	public void setFechaOrden(Timestamp fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public String getGlnProveedor() {
		return this.glnProveedor;
	}

	public void setGlnProveedor(String glnProveedor) {
		this.glnProveedor = glnProveedor;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLugarEntregaCalle() {
		return this.lugarEntregaCalle;
	}

	public void setLugarEntregaCalle(String lugarEntregaCalle) {
		this.lugarEntregaCalle = lugarEntregaCalle;
	}

	public String getLugarEntregaCedula() {
		return this.lugarEntregaCedula;
	}

	public void setLugarEntregaCedula(String lugarEntregaCedula) {
		this.lugarEntregaCedula = lugarEntregaCedula;
	}

	public String getLugarEntregaCiudad() {
		return this.lugarEntregaCiudad;
	}

	public void setLugarEntregaCiudad(String lugarEntregaCiudad) {
		this.lugarEntregaCiudad = lugarEntregaCiudad;
	}

	public String getLugarEntregaGlnCliente() {
		return this.lugarEntregaGlnCliente;
	}

	public void setLugarEntregaGlnCliente(String lugarEntregaGlnCliente) {
		this.lugarEntregaGlnCliente = lugarEntregaGlnCliente;
	}

	public String getLugarEntregaNombre() {
		return this.lugarEntregaNombre;
	}

	public void setLugarEntregaNombre(String lugarEntregaNombre) {
		this.lugarEntregaNombre = lugarEntregaNombre;
	}

	public String getLugarEntregaTelefono() {
		return this.lugarEntregaTelefono;
	}

	public void setLugarEntregaTelefono(String lugarEntregaTelefono) {
		this.lugarEntregaTelefono = lugarEntregaTelefono;
	}

	public String getProveedorCedula() {
		return this.proveedorCedula;
	}

	public void setProveedorCedula(String proveedorCedula) {
		this.proveedorCedula = proveedorCedula;
	}

	public String getProveedorContactoDepto() {
		return this.proveedorContactoDepto;
	}

	public void setProveedorContactoDepto(String proveedorContactoDepto) {
		this.proveedorContactoDepto = proveedorContactoDepto;
	}

	public String getProveedorContactoEmpleado() {
		return this.proveedorContactoEmpleado;
	}

	public void setProveedorContactoEmpleado(String proveedorContactoEmpleado) {
		this.proveedorContactoEmpleado = proveedorContactoEmpleado;
	}

	public String getProveedorContactoTelefono() {
		return this.proveedorContactoTelefono;
	}

	public void setProveedorContactoTelefono(String proveedorContactoTelefono) {
		this.proveedorContactoTelefono = proveedorContactoTelefono;
	}

	public String getProveedorNombre() {
		return this.proveedorNombre;
	}

	public void setProveedorNombre(String proveedorNombre) {
		this.proveedorNombre = proveedorNombre;
	}

	public String getSeqIntercambio() {
		return this.seqIntercambio;
	}

	public void setSeqIntercambio(String seqIntercambio) {
		this.seqIntercambio = seqIntercambio;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalDescuento() {
		return this.totalDescuento;
	}

	public void setTotalDescuento(String totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public String getTotalItems() {
		return this.totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public String getTotalIva() {
		return this.totalIva;
	}

	public void setTotalIva(String totalIva) {
		this.totalIva = totalIva;
	}

	public String getTotalPaquetes() {
		return this.totalPaquetes;
	}

	public void setTotalPaquetes(String totalPaquetes) {
		this.totalPaquetes = totalPaquetes;
	}

}