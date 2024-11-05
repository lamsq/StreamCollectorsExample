import java.util.*;
import java.util.stream.Collectors;

class Order {
    private String prod;
    private double price;

    public Order(String prod, double price) {
        this.prod = prod;
        this.price = price;
    }

    public String getProduct() {
        return prod;
    }

    public double getPrice() {
        return price;
    }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, Double> totalCostByProduct = orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getPrice)));

        List<Map.Entry<String, Double>> sortedProducts = totalCostByProduct.entrySet().stream().sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())).collect(Collectors.toList());

        List<Map.Entry<String, Double>> topThreeProducts = sortedProducts.stream().limit(3).collect(Collectors.toList());

        System.out.println("Три самых дорогих товара и их стоимость:");
        topThreeProducts.forEach(entry -> System.out.println(entry.getKey()+", стоимость: "+entry.getValue())
        );
    }
}
