package com.api_estoque.controller;

import com.api_estoque.model.Estoque;
import com.api_estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/produtos")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    //GET
    @GetMapping
    //listar todos os produtos
    public ResponseEntity<List<Estoque>> listarEstoque() {
        List<Estoque> produtos = estoqueService.listarEstoque();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{produto_id}")
    //buscar um produto por id
    public ResponseEntity<Estoque> buscarProduto(@PathVariable Long produto_id) {
        Estoque estoque = estoqueService.buscarProduto(produto_id);
        return ResponseEntity.ok(estoque);
    }

    //POST
    @PostMapping
    public ResponseEntity<Estoque> cadastrarProduto(@RequestBody Estoque estoque) {
        Estoque produtoCadastrado = estoqueService.cadastrarProduto(estoque);
        return new ResponseEntity<>(produtoCadastrado, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/{produto_id}")
    public ResponseEntity<Estoque> atualizarProduto(@PathVariable Long produto_id, @RequestBody Estoque produtoAtualizado) {
        try {
            Estoque estoqueAtualizado = estoqueService.atualizarProduto(produto_id, produtoAtualizado);
            return new ResponseEntity<>(estoqueAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @DeleteMapping("/{produto_id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long produto_id) {
        estoqueService.deletarProduto(produto_id);
        return ResponseEntity.noContent().build();
    }
}
