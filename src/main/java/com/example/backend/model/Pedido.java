package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_run", referencedColumnName = "run", nullable = false)
    private Usuario usuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles; // Contiene id_producto y cantidad_producto

    @Column(nullable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();
    
    @Column(nullable = false)
    private Double total; // Total de la suma de todos los subtotales de DetallePedido
}

 


/*
    @Id
    @Column(nullable = false)
    private Long idPedido;

    @Column(nullable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column(nullable = false)
    private Double total;

    @Column(nullable=false)
    private int cantidad
    
}

 */

