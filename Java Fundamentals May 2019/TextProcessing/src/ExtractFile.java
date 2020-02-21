import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filePath = scanner.nextLine();

        String fileNameAndExtension = filePath.substring(filePath.lastIndexOf("\\") + 1);

        String name = fileNameAndExtension.substring(0, fileNameAndExtension.indexOf("."));
        String extension = fileNameAndExtension.substring(fileNameAndExtension.indexOf(".") + 1);

        System.out.println("File name: " + name);
        System.out.println("File extension: " + extension);
    }
}
