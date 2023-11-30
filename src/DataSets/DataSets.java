package DataSets;

import java.util.ArrayList;
import java.util.Random;

public class DataSets {

    double X, Y;
    public double[] B0;
    public double[] B1;
    public int contador;
    public double fitness;

    public DataSets(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public DataSets(int contador, double[] B0, double[] B1, double fitness) {
        this.contador = contador;
        this.B0 = B0;
        this.B1 = B1;
        this.fitness = fitness;
    }

    public static ArrayList<DataSets> LeerDatos() {

        ArrayList<DataSets> Datos = new ArrayList<>();

        Datos.add(new DataSets(108, 95));
        Datos.add(new DataSets(115, 96));
        Datos.add(new DataSets(106, 95));
        Datos.add(new DataSets(97, 97));
        Datos.add(new DataSets(95, 93));
        Datos.add(new DataSets(91, 94));
        Datos.add(new DataSets(97, 95));
        Datos.add(new DataSets(83, 93));
        Datos.add(new DataSets(83, 92));
        Datos.add(new DataSets(78, 86));
        Datos.add(new DataSets(54, 73));
        Datos.add(new DataSets(67, 80));
        Datos.add(new DataSets(56, 65));
        Datos.add(new DataSets(53, 69));
        Datos.add(new DataSets(61, 77));
        Datos.add(new DataSets(81, 87));
        Datos.add(new DataSets(78, 89));
        Datos.add(new DataSets(30, 60));
        Datos.add(new DataSets(45, 63));
        Datos.add(new DataSets(99, 95));
        Datos.add(new DataSets(32, 61));
        Datos.add(new DataSets(25, 55));
        Datos.add(new DataSets(28, 56));
        Datos.add(new DataSets(90, 94));
        Datos.add(new DataSets(89, 93));

        return Datos;
    }

    public static ArrayList<DataSets> DatosPoblacionInicial() {
        ArrayList<DataSets> Datos = new ArrayList<>();
        double Poblacion = 6;

        //Elegir los grados
        int Genes = 1;
        Random random = new Random();

        for (int i = 1; i <= Poblacion; i++) {

            double[] randomB0 = new double[Genes];
            double[] randomB1 = new double[Genes];

            for (int j = 0; j < Genes; j++) {
                randomB0[j] = 40 + random.nextDouble() * 10;
                randomB1[j] = 0.8 + random.nextDouble() * 0.2;
            }

            double fitness = calcularFitness(Datos, randomB0[0], randomB1[0]);

            Datos.add(new DataSets(i,randomB0, randomB1, fitness));
        }

        return Datos;
    }


    public static double calcularFitness(ArrayList<DataSets> Datos, double B0, double B1) {
        double error, x, y;
        double SSE = 0;

        for (DataSets p : Datos) {
            x = p.X;
            y = p.Y;
            double residuo = y - (B0 + B1 * x);
            SSE += residuo * residuo;
        }

        double RMSE = Math.sqrt(SSE / Datos.size());
        error = 1 / (1 + RMSE);  // Usando RMSE para calcular el error

        if (error <= 0 || error > 1) {
            error = 0;
        }

        return error;
    }

}
