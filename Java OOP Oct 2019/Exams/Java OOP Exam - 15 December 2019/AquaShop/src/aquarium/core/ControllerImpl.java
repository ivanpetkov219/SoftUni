package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private Repository decorationRepository;
    private List<Aquarium> aquariums;

    public ControllerImpl(){
        this.aquariums = new ArrayList<>();
        this.decorationRepository = new DecorationRepository();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        Aquarium aquarium;

        if(aquariumType.equals("FreshwaterAquarium")){
            aquarium = new FreshwaterAquarium(aquariumName);
        }else if(aquariumType.equals("SaltwaterAquarium")){
            aquarium = new SaltwaterAquarium(aquariumName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }

        aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;

        if(type.equals("Ornament")){
            decoration = new Ornament();
        }else if(type.equals("Plant")){
            decoration = new Plant();
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }

        decorationRepository.add(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration decoration = this.decorationRepository.findByType(decorationType);

        if(decoration == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        if(aquarium != null){
            this.decorationRepository.remove(decoration);
            aquarium.addDecoration(decoration);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
        Fish fish = null;
        if(aquarium != null){

            if(fishType.equals("FreshwaterFish")){
                fish = new FreshwaterFish(fishName,fishSpecies,price);
            }else if (fishType.equals("SaltwaterFish")){
                fish = new SaltwaterFish(fishName, fishSpecies, price);
            }else {
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
            }
        }

        if(!checkIfWaterIsSuitableForFish(aquarium, fishType)){
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        if(fish != null){
            aquarium.addFish(fish);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName );
    }

    private boolean checkIfWaterIsSuitableForFish(Aquarium aquarium, String fishType) {
        boolean waterIsSuiatble = true;
        if(fishType.equals("FreshwaterFish")){
            if(aquarium.getClass().getSimpleName().equals("FreshwaterAquarium")){
                waterIsSuiatble = true;
            }else {
                waterIsSuiatble = false;
            }
        }
        if(fishType.equals("SaltwaterFish")){
            if(aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")){
                waterIsSuiatble = true;
            }else {
                waterIsSuiatble = false;
            }
        }
        return waterIsSuiatble;
    }

    @Override
    public String feedFish(String aquariumName) {

        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        if(aquarium != null){
            aquarium.feed();
        }

        return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {

        double fishPrice = 0;
        double decorationPrice = 0;
        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
        if(aquarium != null){
            decorationPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
            fishPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        }

        double totalPrice = fishPrice + decorationPrice;


        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, totalPrice);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
        }
        return sb.toString().trim();
    }
}
