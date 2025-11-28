package com.example.backend;
import com.example.backend.model.Producto;
import com.example.backend.repository.ProductoRepository;
import com.example.backend.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductoTest {

    // Simula la capa de acceso a datos
    @Mock
    private ProductoRepository productoRepository;

    // Inyecta las simulaciones en el servicio, que es la clase que probamos
    @InjectMocks
    private ProductoService productoService;
    
    private Producto torta;
    private Producto postre;

    @BeforeEach
    void setUp() {
        // --- Creamos las instancias de prueba asignando el ID manualmente ---
        
        torta = new Producto();
        torta.setId(1L); // ID asignado manualmente
        torta.setNombre_producto("Tarta de Chocolate"); 
        torta.setPrecio(150.0);
        torta.setDescripcion("La mejor tarta");
        torta.setCategoria("Torta");
        
        postre = new Producto();
        postre.setId(2L); // ID asignado manualmente
        postre.setNombre_producto("Mousse de Fresa"); 
        postre.setPrecio(50.0);
        postre.setDescripcion("Postre ligero");
        postre.setCategoria("Postre");
    }

    @Test
    void testFindAll() {
        // 1. Simulación: Cuando se llame a findAll(), devuelve nuestra lista de mocks.
        when(productoRepository.findAll()).thenReturn(Arrays.asList(torta, postre));
        
        // 2. Ejecución
        List<Producto> productos = productoService.findAll();
        
        // 3. Verificación
        assertEquals(2, productos.size(), "Debe retornar 2 productos.");
    }

    @Test
    void testFindByCategoria_Torta() {
        // 1. Simulación: Cuando se filtre por "Torta", devuelve solo la torta.
        when(productoRepository.findByCategoria("Torta")).thenReturn(Arrays.asList(torta));
        
        // 2. Ejecución
        List<Producto> tortas = productoService.findByCategoria("Torta");
        
        // 3. Verificación
        assertEquals(1, tortas.size(), "Debe retornar 1 producto para la categoría Torta.");
        assertEquals("Tarta de Chocolate", tortas.get(0).getNombre_producto()); 
    }
}