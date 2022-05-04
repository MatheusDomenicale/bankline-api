package com.dio.santander.bankline.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.santander.bankline.api.dto.NovaMovimentaocao;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired 
	private CorrentistaRepository correntistaRepository;

	public void save(NovaMovimentaocao novaMovimentaocao) {
		
		Movimentacao movimentacao = new Movimentacao();
		
		//Double valor = novaMovimentaocao.getTipo()==MovimentacaoTipo.DESPESA ? novaMovimentaocao.getValor() : novaMovimentaocao.getValor() * -1;
		
		Double valor = novaMovimentaocao.getValor();
		if(novaMovimentaocao.getTipo() == MovimentacaoTipo.DESPESA)
			valor = valor * -1;
		
		movimentacao.setDataHora(LocalDateTime.now());
		movimentacao.setDescricao(novaMovimentaocao.getDescricao());
		movimentacao.setIdConta(novaMovimentaocao.getIdConta());
		movimentacao.setTipo(novaMovimentaocao.getTipo());
		movimentacao.setValor(valor);
		
		Correntista correntista = correntistaRepository.findById(novaMovimentaocao.getIdConta()).orElse(null);
		if(correntista != null) {
			correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
			correntistaRepository.save(correntista);
		}
		
		
		repository.save(movimentacao);
		
	}

}
