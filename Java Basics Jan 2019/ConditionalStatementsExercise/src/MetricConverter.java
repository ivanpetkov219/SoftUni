import java.util.Scanner;

public class MetricConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double inputValue = Double.parseDouble(scanner.nextLine());
        String inputMetric = scanner.nextLine();
        String outputMetric = scanner.nextLine();

        switch (inputMetric) {
            case "mm":
                if (outputMetric.equals("cm")){
                    double result = inputValue * 0.1;
                    System.out.printf("%.3f", result);
                }else if (outputMetric.equals("m")){
                    double result = inputValue * 0.001;
                    System.out.printf("%.3f", result);
                }
                break;
            case "cm":
                if (outputMetric.equals("mm")){
                    double result = inputValue * 10;
                    System.out.printf("%.3f", result);
                }else if (outputMetric.equals("m")){
                    double result = inputValue * 0.01;
                    System.out.printf("%.3f", result);
                }
                break;
            case "m":
                if (outputMetric.equals("cm")){
                    double result = inputValue * 100;
                    System.out.printf("%.3f", result);
                }else if (outputMetric.equals("mm")){
                    double result = inputValue * 1000;
                    System.out.printf("%.3f", result);
                }
                break;

        }
    }
}
