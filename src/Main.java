import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of unknowns: ");
        int numberOfUnknowns = scanner.nextInt();
        double[][] matrix = new double[numberOfUnknowns][numberOfUnknowns + 1];
        System.out.println("Enter matrix (n) x (n+1): ");
        for (int i = 0; i < numberOfUnknowns; i++) {
            for (int j = 0; j <= numberOfUnknowns; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        Gauss gauss = new Gauss(numberOfUnknowns, matrix);
        gauss.solve();
    }
}
