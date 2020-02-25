package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Map<String, Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new LinkedHashMap<>();
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(motocrossWorldChampionship.common.ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return new ArrayList<>(this.riders.values());
    }

    @Override
    public void addRider(Rider rider) {
        if (rider == null) {
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }
        if (rider.getMotorcycle() == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, rider.getName()));
        }
        if (riders.containsKey(rider.getName())) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_ALREADY_ADDED, rider.getName(), this.name));
        }
        riders.put(rider.getName(), rider);
    }
}
