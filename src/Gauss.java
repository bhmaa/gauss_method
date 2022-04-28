public class Gauss {
    private final int numberOfUnknowns;
    private final double[][] coefficients;
    private final double[] solution;

    public Gauss(int numberOfUnknowns, double[][] coefficients) {
        this.numberOfUnknowns = numberOfUnknowns;
        this.coefficients = coefficients;
        solution = new double[numberOfUnknowns];
    }

    private void swap(int i, int j) {
        for (int k = 0; k <= numberOfUnknowns; k++) {
            double temp = coefficients[i][k];
            coefficients[i][k] = coefficients[j][k];
            coefficients[j][k] = temp;
        }
    }

    private int reduce() {
        for (int i = 0; i < numberOfUnknowns; i++) {
            int index = i;
            double maxValue = Math.abs(coefficients[index][i]);
            for (int j = i + 1; j < numberOfUnknowns; j++) {
                if (Math.abs(coefficients[j][i]) > maxValue) {
                    maxValue = Math.abs(coefficients[j][i]);
                    index = j;
                }
            }
            if (coefficients[i][index] == 0) {
                return i;
            }
            if (index != i) {
                swap(i, index);
            }
            for (int j = i + 1; j < numberOfUnknowns; j++) {
                double factor = coefficients[j][i] / coefficients[i][i];
                for (int k = i + 1; k <= numberOfUnknowns; k++) {
                    coefficients[j][k] -= coefficients[i][k] * factor;
                }
                coefficients[j][i] = 0;
            }
        }
        return -1;
    }

    private void calculate() {
        for (int i = numberOfUnknowns - 1; i >= 0; i--) {
            solution[i] = coefficients[i][numberOfUnknowns];
            for (int j = i + 1; j < numberOfUnknowns; j++) {
                solution[i] -= coefficients[i][j] * solution[j];
            }
            solution[i] = solution[i] / coefficients[i][i];
        }
    }

    public double[] solve() throws SolutionException {
        int singular_flag = reduce();
        if (singular_flag != -1) {
            if (coefficients[singular_flag][numberOfUnknowns] != 0) {
                throw new SolutionException("Inconsistent system");
            } else {
                throw new SolutionException("May have infinitely many solutions");
            }
        }
        calculate();
        return solution;
    }
}