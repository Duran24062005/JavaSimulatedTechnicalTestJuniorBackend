package org.example;

import java.nio.file.Path;
import java.util.List;

import org.example.data.ProductDataLoader;
import org.example.model.ProductModel;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.example.view.ProductView;

public class Main {

    private static final Path DEFAULT_PRODUCTS_DATA_PATH = Path.of("data/products.csv");

    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();
        ProductView view = new ProductView();
        ProductService service = new ProductService(repo, view);

        loadProducts(repo, view, getProductsDataPath(args));

        boolean system = true;
        while (system) {
            switch (view.menu()) {
                case 1:
                    service.showProducts();
                    break;
                case 2:
                    service.showProduct();
                    break;
                case 3:
                    service.createProduct();
                    break;
                case 4:
                    service.updateProduct();
                    break;
                case 5:
                    service.deleteProduct();
                    break;
                case 6:
                    system = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static Path getProductsDataPath(String[] args) {
        if (args.length == 0) {
            return DEFAULT_PRODUCTS_DATA_PATH;
        }

        return Path.of(args[0]);
    }

    private static void loadProducts(ProductRepository repo, ProductView view, Path productsDataPath) {
        ProductDataLoader loader = new ProductDataLoader();

        try {
            List<ProductModel> products = loader.loadProducts(productsDataPath);

            for (ProductModel product : products) {
                repo.createNewProduct(product);
            }

            if (!products.isEmpty()) {
                view.showMessage("Productos de prueba cargados: " + products.size() + "\n");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showMessage("No se pudieron cargar los productos de prueba: " + e.getMessage() + "\n");
        }
    }
}
