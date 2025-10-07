package com.api_estoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table (name = "movimentacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacoes {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long movimentacao_id;

    @Column (nullable = false)
    private String tipoMovimentacao;

    @Column (nullable = false)
    private Integer quantidade;

    @Column (nullable = false)
    private LocalDate data;

    @Column (nullable = true)
    private String observacao;

    @ManyToOne
    @JoinColumn (name = "produto_id", nullable = false)
    private Estoque produto;
}
