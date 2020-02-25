package trafficlight;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        TrafficLight[] trafficLights = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(TrafficLight::valueOf)
                .toArray(TrafficLight[]::new);

        int n = scanner.nextInt();

        TrafficLight[] lights = TrafficLight.values();

        StringBuilder sb = new StringBuilder();

        while (n-- > 0){
            for (int i = 0; i < trafficLights.length; i++) {
                int index = (trafficLights[i].ordinal() + 1) % lights.length;
                trafficLights[i] = lights[index];
                sb.append(trafficLights[i].toString()).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }
}
