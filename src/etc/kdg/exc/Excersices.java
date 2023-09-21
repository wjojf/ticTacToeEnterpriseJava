package etc.kdg.exc;

import etc.helpers.SortHelpers;
import etc.models.Car;
import etc.models.CarFactory;
import etc.models.User;
import etc.models.UserFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Excersices {
    public static void testTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number 1: ");
        int number1 = scanner.nextInt();

        System.out.print("Enter number 2: ");
        int number2 = scanner.nextInt();

        System.out.print("Enter operation 1-4: ");
        int operation = scanner.nextInt();

        int operationResult;
        String operationOutput;

        switch (operation) {
            case 1 -> {
                operationResult = number1 + number2;
                operationOutput = MessageFormat.format(
                        "{0} + {1} = {2}",
                        number1, number2, operationResult
                );
            }
            case 2 -> {
                operationResult = number1 - number2;
                operationOutput = MessageFormat.format(
                        "{0} - {1} = {2}",
                        number1, number2, operationResult
                );
            }
            case 3 -> {
                operationResult = number1 * number2;
                operationOutput = MessageFormat.format(
                        "{0} * {1} = {2}",
                        number1, number2, operationResult
                );
            }
            case 4 -> {
                operationResult = number1 / number2;
                operationOutput = MessageFormat.format(
                        "{0} - {1} = {2}",
                        number1, number2, operationResult
                );
            }
            default -> operationOutput = "INVALID OPERATION";
        }

        System.out.println(operationOutput);
    }

    public static void testModel() {
        User user = UserFactory.newUser(1, "test@email.com");
        System.out.println(user.getHelloMessage());
    }

    public static void testSort() {
        Integer[] arr = {-3, 5, 0, -1, 3, 7};

        System.out.println(Arrays.toString(arr));

        SortHelpers.bubbleSort(arr);

        System.out.println(Arrays.toString(arr));

        Double[] arrDouble = {-1.3, 1.3, 0.35, 2.72, -9.2};
        System.out.println(Arrays.toString(arrDouble));

        SortHelpers.bubbleSort(arrDouble);
        System.out.println(Arrays.toString(arrDouble));

    }

    public static void testInstancesFactory() {
        User user = UserFactory.newRandomUser();
        Car car = CarFactory.newRandomCar();

        System.out.println(user.getHelloMessage());
        System.out.println(car.getStartMessage());
    }
}
