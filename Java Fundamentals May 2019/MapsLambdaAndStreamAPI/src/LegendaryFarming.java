import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Integer> keyMaterials = new HashMap<>();
        Map<String, Integer> junk = new TreeMap<>();

        keyMaterials.put("motes", 0);
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);

        boolean itemObtained = false;
        String keyMaterialObtained = "";

        while (!itemObtained) {

            String[] input = scanner.nextLine().split("\\s+");

            for (int i = 0; i < input.length; i += 2) {
                int value = Integer.parseInt(input[i]);
                String material = input[i + 1].toLowerCase();

                if (material.equals("shards") || material.equals("motes") || material.equals("fragments")) {

                    int newValue = keyMaterials.get(material) + value;
                    if(newValue >= 250){
                        itemObtained = true;
                        keyMaterialObtained = material;
                        keyMaterials.put(material, newValue - 250);
                        break;
                    }else {
                        keyMaterials.put(material, newValue);
                    }
                } else {
                    if(!junk.containsKey(material)){
                        junk.put(material, value);
                    }else {
                        int newValue = junk.get(material)+ value;
                        junk.put(material, newValue);
                    }
                }
            }

            if(keyMaterialObtained.equals("motes")){
                System.out.println("Dragonwrath obtained!");
            }else if(keyMaterialObtained.equals("shards")){
                System.out.println("Shadowmourne obtained!");
            }else if(keyMaterialObtained.equals("fragments")){
                System.out.println("Valanyr obtained!");
            }


        }

        keyMaterials.entrySet().stream().sorted((first, second) -> {
            int result = second.getValue().compareTo(first.getValue());

            if (result == 0){
                result = first.getKey().compareTo(second.getKey());
            }
            return  result;

        }).forEach(entry -> {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        });


        for (Map.Entry<String, Integer> entry : junk.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }
    }

}
