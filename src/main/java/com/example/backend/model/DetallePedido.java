package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Se recomienda usar una clave compuesta (Composite Key) para esta tabla,
// pero por simplicidad inicial, usaremos un ID propio auto-generado.

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad; 

    @Column(nullable = false)
    private Double precioUnitario; // Guardar el precio al momento del pedido (evita problemas si el precio de Producto cambia después)
    
    @Column(nullable = false)
    private Double subtotal; // cantidad * precioUnitario
}