package etc.models;

public class Car {

    private String model;
    private String brand;

    private String color;

    public Car(String model, String brand, String color) {
        this.model = model;
        this.brand = brand;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }
    private void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStartMessage() {
        return "%s %s successfully started!".formatted(this.color, this.brand);
    }
}
