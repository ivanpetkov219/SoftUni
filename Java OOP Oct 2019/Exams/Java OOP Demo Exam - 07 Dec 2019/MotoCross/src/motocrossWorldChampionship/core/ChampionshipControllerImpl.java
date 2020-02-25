package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.*;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {
    private Repository<Rider> riderRepository;
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Race> raceRepository;


    public ChampionshipControllerImpl(Repository<Rider> riderRepository,
                                      Repository<Motorcycle> motorcycleRepository,
                                      Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }


    @Override
    public String createRider(String riderName) {
        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);
        return String.format(OutputMessages.RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = null;

        switch (type) {
            case "Speed":
                motorcycle = new SpeedMotorcycle(model, horsePower);
                break;
            case "Power":
                motorcycle = new PowerMotorcycle(model, horsePower);
                break;
        }
        motorcycleRepository.add(motorcycle);

        return String.format(OutputMessages.MOTORCYCLE_CREATED, motorcycle.getClass().getSimpleName(), model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        Motorcycle motorcycle = motorcycleRepository.getByName(motorcycleModel);

        if (motorcycle == null) {
            throw new NullPointerException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }

        rider.addMotorcycle(motorcycle);

        return String.format(OutputMessages.MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {

        Race race = raceRepository.getByName(raceName);

        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Rider rider = riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }

        race.addRider(rider);

        return String.format(OutputMessages.RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Collection<Rider> riders = race.getRiders();

        if (riders.size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }



        riders = riders.stream().sorted((first, second) -> {
            double sort = second.getMotorcycle().calculateRacePoints(race.getLaps()) -
                    first.getMotorcycle().calculateRacePoints(race.getLaps());
            return (int) (sort * 100);

        }).collect(Collectors.toList());

        ((List<Rider>) riders).get(0).winRace();

        return String.format(OutputMessages.RIDER_FIRST_POSITION, ((List<Rider>) riders).get(0).getName(), raceName) + System.lineSeparator() +
                String.format(OutputMessages.RIDER_SECOND_POSITION,((List<Rider>) riders).get(1).getName(), raceName) + System.lineSeparator() +
                String.format(OutputMessages.RIDER_THIRD_POSITION, ((List<Rider>) riders).get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {

        Race race = new RaceImpl(name, laps);

        raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
