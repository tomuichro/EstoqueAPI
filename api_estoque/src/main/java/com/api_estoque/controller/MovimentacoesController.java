package com.api_estoque.controller;

import com.api_estoque.model.Movimentacoes;
import com.api_estoque.service.MovimentacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacoesController {
    @Autowired
    MovimentacoesService movimentacoesService;

    //POST
    //registrarMovimentacoes
    @PostMapping
    public ResponseEntity<Movimentacoes> registrarMovimentacao (@RequestBody Movimentacoes movimentacoes) {
        Movimentacoes movimentacaoRegistrada = movimentacoesService.registrarMovimentacao(movimentacoes);
        return new ResponseEntity<>(movimentacaoRegistrada, HttpStatus.CREATED);
    }

    //GET
    //listarMovimentacoes
    @GetMapping("/{produto_id}")
    public ResponseEntity<List<Movimentacoes>> listarMovimentacoes (@PathVariable Long produto_id) {
        List<Movimentacoes> produtoMovimentacoes = movimentacoesService.listarMovimentacoes(produto_id);
        return new ResponseEntity<>(produtoMovimentacoes, HttpStatus.OK);
    }
}
