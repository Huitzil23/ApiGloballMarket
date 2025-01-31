package com.mx.api.globall.market.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_TipoPago",catalog = "dbo")
public class TipoPago implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "IdTipoPago")
    public Integer idTipoPago; 
    @Column(name = "ClavePago")
    public String clavePago;
    @Column(name = "TipoPago")
    public String tipoPago;
    @Column(name = "Estatus")
    public Integer estatus;
}
