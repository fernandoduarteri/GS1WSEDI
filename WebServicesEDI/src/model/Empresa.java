package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Empresas database table.
 * 
 */
@Entity
@Table(name="Empresas")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdEmpresa")
	private int idEmpresa;

	@Column(name="Celular")
	private String celular;

	@Column(name="CorreoAsig")
	private String correoAsig;
	
	@Column(name="GLNProveedor")
	private String GLNProveedor;

	@Lob
	@Column(name="LlavePGP")
	private String llavePGP;

	@Column(name="NoContrato")
	private String noContrato;

	@Column(name="RazonSocial")
	private String razonSocial;

	@Column(name="UsaPGP")
	private boolean usaPGP;

	@Column(name="UsuarioWEB")
	private boolean usuarioWEB;

	public Empresa() {
	}

	public int getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreoAsig() {
		return this.correoAsig;
	}

	public void setCorreoAsig(String correoAsig) {
		this.correoAsig = correoAsig;
	}

	public String getGLNProveedor() {
		return this.GLNProveedor;
	}

	public void setGLNProveedor(String GLNProveedor) {
		this.GLNProveedor = GLNProveedor;
	}

	public String getLlavePGP() {
		return this.llavePGP;
	}

	public void setLlavePGP(String llavePGP) {
		this.llavePGP = llavePGP;
	}

	public String getNoContrato() {
		return this.noContrato;
	}

	public void setNoContrato(String noContrato) {
		this.noContrato = noContrato;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public boolean getUsaPGP() {
		return this.usaPGP;
	}

	public void setUsaPGP(boolean usaPGP) {
		this.usaPGP = usaPGP;
	}

	public boolean getUsuarioWEB() {
		return this.usuarioWEB;
	}

	public void setUsuarioWEB(boolean usuarioWEB) {
		this.usuarioWEB = usuarioWEB;
	}

}