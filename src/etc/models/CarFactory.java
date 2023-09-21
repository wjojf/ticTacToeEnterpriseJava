package etc.models;
import java.util.Random;

public class CarFactory {
    public static Car newCar(String model, String brand, String color) {
        return new Car(model, brand, color);
    }

    public static Car newRandomCar() {
        String[] colors = {"yellow", "red", "green", "blue"};
        String [] models = {"model 1", "model 2", "model 3"};
        String [] brands = {"mercedez", "chevrolet", "toyota"};

        Random rndm = new Random();

        String color = colors[rndm.nextInt(colors.length)];
        String model = models[rndm.nextInt(models.length)];
        String brand = brands[rndm.nextInt(brands.length)];

        return new Car(model, brand, color);
    }

}
