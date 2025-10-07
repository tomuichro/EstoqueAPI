package com.api_estoque.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long produto_id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = true)
    private String descricao;

    @Column (nullable = false)
    private Double preco;

    @Column (nullable = false)
    private Integer quantidadeEstoque;

    @Column (nullable = false)
    private String categoria;

    @Column (nullable = false)
    private String fornecedor;
}
