package com.api_estoque.service;

import com.api_estoque.model.Estoque;
import com.api_estoque.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    //GET
    //listar os produtos do estoque
    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    //buscar um produto por id
    public Estoque buscarProduto (Long produto_id) {
        return estoqueRepository.findById(produto_id).orElseThrow(
                () -> new EntityNotFoundException("produto não encontrado")
        );
    }

    //POST
    //cadastrar um novo produto
    public Estoque cadastrarProduto (Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    //PUT
    //atualizar produto
    public Estoque atualizarProduto (Long produto_id, Estoque produtoAtualizado) {
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(produto_id);
        if (estoqueOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();
            estoque.setNome(produtoAtualizado.getNome());
            estoque.setDescricao(produtoAtualizado.getDescricao());
            estoque.setPreco(produtoAtualizado.getPreco());
            estoque.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            estoque.setCategoria(produtoAtualizado.getCategoria());
            estoque.setFornecedor(produtoAtualizado.getFornecedor());
        }
        throw new RuntimeException("Livro não encontrado");
    }

    //DELETE
    //deletar produto
    public void deletarProduto (Long produto_id) {
        if (!estoqueRepository.existsById(produto_id)) {
            throw new EntityNotFoundException("produto não encontrado");
        }
        estoqueRepository.deleteById(produto_id);
    }
}
