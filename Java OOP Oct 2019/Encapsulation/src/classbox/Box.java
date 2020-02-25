package classbox;

public class Box {
   private double length;
   private double width;
   private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setLength(double length) {
        if(length <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.length = length;
    }

    public void setWidth(double width) {
        if(width <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    public void setHeight(double height) {
        if(height <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.height = height;
    }

    public double calculateSurfaceArea(){
        double surfaceArea = (2 * length * width) + calculateLateralSurfaceArea();

        return surfaceArea;
    }

    public double calculateLateralSurfaceArea(){
        double lateralSurfaceArea = (2 * length * height) + (2 * width * height);

        return lateralSurfaceArea;
    }

    public double calculateVolume(){
        double volume = length * width * height;
        return volume;
    }
}



