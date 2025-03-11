package com.mx.api.globall.market.utils;

public enum EstatusMarketplace {
	Recibido(0),
	Aceptado(1),
	Procesando(2),
	EnCamino(3),
	Entregado(4),
	EsperaConfirmacion(99);
    
	private Integer value;
	
	private EstatusMarketplace(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
