package com.api_estoque.repository;

import com.api_estoque.model.Estoque;
import com.api_estoque.model.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {
    List<Movimentacoes> findByProduto(Estoque produto);
}
