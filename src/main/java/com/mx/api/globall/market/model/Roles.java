package com.mx.api.globall.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbc_GTC_Roles",catalog = "dbo")
public class Roles {

    @Id
    @Column(name = "IdRol")
    private Integer idRol;
    @Column(name = "Descripcion")
    private String Descripcion;

    public Roles() {}

    public Roles(String Descripcion) {
        super();
        this.Descripcion = Descripcion;
    }
}