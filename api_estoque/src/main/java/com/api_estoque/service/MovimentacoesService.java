package com.api_estoque.service;

import com.api_estoque.model.Estoque;
import com.api_estoque.model.Movimentacoes;
import com.api_estoque.repository.EstoqueRepository;
import com.api_estoque.repository.MovimentacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class MovimentacoesService {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private MovimentacoesRepository movimentacoesRepository;

    //POST
    //registrar movimentação de estoque
    public Movimentacoes registrarMovimentacao(Movimentacoes movimentacoes) {
        //buscando o produto
        Estoque produto = estoqueRepository.findById(movimentacoes.getProduto().getProduto_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        //se o tipo de movimentação for 'entrada', irá acrescentar a quantidade do produto no estoque
        if ("Entrada".equalsIgnoreCase(movimentacoes.getTipo_movimentacao())) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + movimentacoes.getQuantidade());
        //se o tipo de movimentação for 'saída', o sistema irá, primeiro, verificar se há quantidade suficiente no estoque
        } else if ("Saída".equalsIgnoreCase(movimentacoes.getTipo_movimentacao())) {
            //se a quantidade for suficiente, irá subtrair do estoque
            if (produto.getQuantidadeEstoque() >= movimentacoes.getQuantidade()) {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - movimentacoes.getQuantidade());
            //se a quantidade for insuficiente, retornará uma mensagem
            } else {
                throw  new RuntimeException("Estoque insuficiente");
            }

        } else {
            throw new RuntimeException("Tipo de movimentação inválido");
        }

        //salvando a nova quantidade em estoque
        estoqueRepository.save(produto);

        movimentacoes.setProduto(produto);
        movimentacoes.setData(LocalDate.now());
        return movimentacoesRepository.save(movimentacoes);
    }

    //GET
    //listar movimentações de um produto
    public List<Movimentacoes> listarMovimentacoes(Long produto_id) {
        Estoque produto = estoqueRepository.findById(produto_id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return movimentacoesRepository.findByProduto(produto);
    }

}
