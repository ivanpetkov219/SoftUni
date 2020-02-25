package aquarium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Aquarium {
    private String name;
    private int capacity;
    private Collection<Fish> fish;

    public Aquarium(String name, int capacity){
        this.setCapacity(capacity);
        this.setName(name);
        this.fish = new ArrayList<>();
    }

    public String getName() {       //TODO -> check
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException("Invalid aquarium name!");
        }
        this.name = name;
    }

    public int getCapacity() {       //TODO -> check
        return capacity;
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid aquarium capacity!");
        }
        this.capacity = capacity;
    }

    public int getCount() {
        return this.fish.size();       //TODO -> check
    }

    public void add(Fish fish){
        if (this.fish.size() == this.capacity){
            throw new IllegalArgumentException("Aquarium is full!");       //TODO -> check
        }
        this.fish.add(fish);       //TODO -> check
    }

    public void remove(String name) {
        Fish fishToRemove = this.fish.stream()
                .filter(f -> name.equals(f.getName()))
                .findAny()
                .orElse(null);
        if (fishToRemove == null){
            throw new IllegalArgumentException(String.format("Fish with name %s doesn't exist", name));       //TODO -> check
        }
        this.fish.remove(fishToRemove);       //TODO -> check
    }

    public Fish sellFish(String name){
        Fish requestedFish = this.fish.stream()
                .filter(f -> name.equals(f.getName()))
                .findAny()
                .orElse(null);
        if (requestedFish == null){
            throw new IllegalArgumentException(String.format("Fish with name %s doesn't exist", name));       //TODO -> check
        }
        requestedFish.setAvailable(false);       //TODO -> check

        return requestedFish;       //TODO -> check
    }

    public String report(){
        String names = this.fish.stream().map(Fish::getName).collect(Collectors.joining(", "));
        return String.format("Fish available at %s: %s", this.name, names);       //TODO -> check
    }
}
