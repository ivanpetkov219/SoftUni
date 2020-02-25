package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;

public class Main {
    public static void main(String[] args) {


        RiderRepository riderRepository = new RiderRepository();
        MotorcycleRepository motorcycleRepository = new MotorcycleRepository();
        RaceRepository raceRepository = new RaceRepository();


        ChampionshipController controller = new ChampionshipControllerImpl(riderRepository, motorcycleRepository, raceRepository);
        Engine engine = new EngineImpl(controller);

        engine.run();
    }
}
