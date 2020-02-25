package aquarium.models.aquariums;

import aquarium.common.ExceptionMessages;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;


import java.util.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private List<Decoration> decorations;
    private Map<String, Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new LinkedList<>();
        this.fish = new LinkedHashMap<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == this.capacity){
            throw new IllegalStateException("Not enough capacity.");
        }
        this.fish.put(fish.getName(), fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish.getName());
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.values().stream().forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):"
                , this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        if(this.fish.size() == 0){
            sb.append("Fish: none").append(System.lineSeparator());
        }else {
            sb.append("Fish:");
            for (String name : fish.keySet()) {
                sb.append(" " + name);
            }
            sb.append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d", this.decorations.size())).append(System.lineSeparator());

        int aquariumComfort = this.decorations.stream().mapToInt(Decoration::getComfort).sum();
        sb.append(String.format("Comfort: %d", aquariumComfort)).append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public Collection<Fish> getFish() {

        return this.fish.values();
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
