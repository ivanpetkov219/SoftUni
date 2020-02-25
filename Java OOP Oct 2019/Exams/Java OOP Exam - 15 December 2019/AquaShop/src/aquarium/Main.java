package aquarium;

import aquarium.core.Engine;
import aquarium.core.EngineImpl;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;


public class Main {
    public static void main(String[] args) {


        Engine engine = new EngineImpl();
       engine.run();
    }
}
