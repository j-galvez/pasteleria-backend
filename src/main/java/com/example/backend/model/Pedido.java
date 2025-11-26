package com.example.backend.model;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_rut", referencedColumnName = "rut", nullable = false)
    private Usuario usuario;

    @ToString.Exclude // ¡CRUCIAL! Evita el bucle infinito en el método toString()
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles; // Contiene id_producto y cantidad_producto

    @Column(nullable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();
    
    @Column(nullable = false)
    private Double total; // Total de la suma de todos los subtotales de DetallePedido
}