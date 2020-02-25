package shoppingspree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void buyProduct(Product product) {
        if (product.getCost() > this.getMoney()) {
            System.out.printf("%s can't afford %s%n", this.getName(), product.getName());
        } else {
            System.out.printf("%s bought %s%n", this.getName(), product.getName());
            this.products.add(product);
            this.setMoney(this.getMoney() - product.getCost());
        }
    }

    public String getName() {
        return this.name;
    }

    private double getMoney() {
        return this.money;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public String toString() {
        String output = "Nothing bought";

        if (!this.products.isEmpty()) {
            output = this.products.stream().
                    map(Product::getName).
                    collect(Collectors.joining(", "));

        }


        return this.getName() + " - " + output;
    }
}
