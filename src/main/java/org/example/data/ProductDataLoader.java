package org.example.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.example.model.ProductModel;

public class ProductDataLoader {

    private static final int EXPECTED_COLUMNS = 3;

    public List<ProductModel> loadProducts(Path filePath) {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            return parseProducts(Files.readAllLines(filePath));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo " + filePath, e);
        }
    }

    private List<ProductModel> parseProducts(List<String> lines) {
        List<ProductModel> products = new ArrayList<>();

        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index).trim();

            if (line.isBlank() || line.startsWith("#") || isHeader(index, line)) {
                continue;
            }

            products.add(parseProduct(line, index + 1));
        }

        return products;
    }

    private boolean isHeader(int index, String line) {
        return index == 0 && line.equalsIgnoreCase("name,price,stock");
    }

    private ProductModel parseProduct(String line, int lineNumber) {
        String[] columns = line.split(",", -1);

        if (columns.length != EXPECTED_COLUMNS) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. Formato esperado: name,price,stock");
        }

        String name = columns[0].trim();
        double price = parsePrice(columns[1].trim(), lineNumber);
        int stock = parseStock(columns[2].trim(), lineNumber);

        if (name.isBlank()) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. El nombre es obligatorio.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. El precio debe ser mayor que cero.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. El stock no puede ser negativo.");
        }

        ProductModel product = new ProductModel();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    private double parsePrice(String value, int lineNumber) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. El precio debe ser numerico.", e);
        }
    }

    private int parseStock(String value, int lineNumber) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Linea " + lineNumber + " invalida. El stock debe ser numerico.", e);
        }
    }
}
