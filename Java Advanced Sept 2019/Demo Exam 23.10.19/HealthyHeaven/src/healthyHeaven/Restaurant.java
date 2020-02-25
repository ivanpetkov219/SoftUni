package healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Salad> data;


    public Restaurant(String name){
        this.name = name;
        this.data = new ArrayList<>();
    }
    public void add(Salad salad){
        this.data.add(salad);
    }

    public boolean buy(String name){
        if(this.data.contains(name)){
            this.data.remove(name);
            return true;
        }
        return false;
    }

    public String getHealthiestSalad(){
        Salad salad = null;
        int minCalories = Integer.MAX_VALUE;

        for (Salad datum : data) {
            int currentSaladCalories = datum.getTotalCalories();
            if (currentSaladCalories <= minCalories){
                minCalories = currentSaladCalories;
                salad = datum;
            }
        }

        return salad.getName().toString().trim();
    }

    public String generateMenu(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s have %d salads:",
                this.getName(),
                this.data.size())).append(System.lineSeparator());

        for (Salad datum : data) {
            sb.append(datum.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String getName(){
        return this.name;
    }

}
