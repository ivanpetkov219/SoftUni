import java.util.Scanner;

public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        String figure = scanner.nextLine();

        if (figure.equals("square")){
            double sideLenght = Double.parseDouble(scanner.nextLine());
            double squareAre = sideLenght * sideLenght;
            System.out.printf("%.3f", squareAre);
        }else if (figure.equals("rectangle")){
            double sideA = Double.parseDouble(scanner.nextLine());
            double sideB = Double.parseDouble(scanner.nextLine());
            double rectangleArea = sideA * sideB;
            System.out.printf("%.3f", rectangleArea);
        }else if (figure.equals("circle")){
            double radius = Double.parseDouble(scanner.nextLine());
            double circleArea = Math.PI * (radius * radius);
            System.out.printf("%.3f", circleArea);
        }else if (figure.equals("triangle")){
            double sideLenght = Double.parseDouble(scanner.nextLine());
            double sideHeight = Double.parseDouble(scanner.nextLine());
            double triangleArea = (0.5 * sideLenght) * sideHeight;
            System.out.printf("%.3f", triangleArea);
        }

    }
}
