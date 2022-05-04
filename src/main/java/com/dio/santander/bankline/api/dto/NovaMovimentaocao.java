package com.dio.santander.bankline.api.dto;

import com.dio.santander.bankline.api.model.MovimentacaoTipo;

import lombok.Data;

@Data
public class NovaMovimentaocao {
	
	private Integer idConta;
	private String descricao;
	private MovimentacaoTipo tipo;
	private Double valor;
}
