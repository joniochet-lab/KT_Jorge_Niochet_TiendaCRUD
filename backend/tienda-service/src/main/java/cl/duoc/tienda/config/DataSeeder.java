package cl.duoc.tienda.config;

import cl.duoc.tienda.model.Producto;
import cl.duoc.tienda.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DataSeeder(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        if (productoRepository.count() > 0) {
            return;
        }

        productoRepository.save(new Producto(null, "Notebook Estudiante 14", "Notebook liviano para clases y programación básica.", "Computación", 459990.0, 8, ""));
        productoRepository.save(new Producto(null, "Mouse Inalámbrico", "Mouse cómodo para trabajo diario y laboratorio.", "Accesorios", 12990.0, 25, ""));
        productoRepository.save(new Producto(null, "Teclado Mecánico Compacto", "Teclado compacto con switches mecánicos.", "Accesorios", 39990.0, 12, ""));
        productoRepository.save(new Producto(null, "Audífonos Bluetooth", "Audífonos inalámbricos con estuche de carga.", "Audio", 24990.0, 18, ""));
    }
}
