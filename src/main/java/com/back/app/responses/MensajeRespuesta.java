package com.back.app.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MensajeRespuesta {
	private String message;
		
	public MensajeRespuesta(String message) {
		this.message = message;
	}
}
