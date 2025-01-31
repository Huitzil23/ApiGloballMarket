package com.mx.api.globall.market.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseExecProc {
	private Integer respuesta;
	private String message;
	public ResponseExecProc(Integer respuesta, String message) {
		super();
		this.respuesta = respuesta;
		this.message = message;
	}
	public ResponseExecProc() {
		super();
	}
}
