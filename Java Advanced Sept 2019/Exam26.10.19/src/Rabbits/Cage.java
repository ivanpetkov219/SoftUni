package Rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public void add(Rabbit rabbit){
        if(this.data.size() < this.capacity) {
            this.data.add(rabbit);
        }
    }
    public boolean removeRabbit(String name){
        if(this.data.contains(name)){
            removeRabbit(name);
            return true;
        }
       return  false;
    }
    public void removeSpecies(String species){
        for (Rabbit rabbit : data) {
            String currentSpecie = rabbit.getSpecies();
            if(currentSpecie.equals(species)){
                this.data.remove(rabbit);
            }
        }
    }

    public void sellRabbit(String name){
        for (Rabbit rabbit : data) {
            if(rabbit.getName().equals(name)){
                rabbit.setAvailable(false);
                System.out.println(rabbit);
            }
        }
    }
    public List<Rabbit> sellRabbitBySpecies(String species){
        for (Rabbit rabbit : data) {
            if(rabbit.getSpecies().equals(species)){
                List<Rabbit> soldSpecies = new ArrayList<>();
                soldSpecies.add(rabbit);
            }
        }

    }

    public int count(){
        return this.data.size();
    }

    public String report(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Rabbits available at %s:", this.name)).append(System.lineSeparator());

        for (Rabbit rabbit : data) {
            if(rabbit.isAvailable()){
                sb.append(rabbit).append(System.lineSeparator());
            }
        }


        return sb.toString();
    }

}
