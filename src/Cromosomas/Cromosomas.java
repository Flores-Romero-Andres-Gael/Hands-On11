package Cromosomas;

import DataSets.DataSets;
import Roulette.Roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cromosomas {

    public static ArrayList<DataSets> Datos = new ArrayList<>(DataSets.DatosPoblacionInicial());
    public static ArrayList<DataSets> NuevaPoblacion = new ArrayList<>();
    public static final double TASA_CRUCE = 0.7;
    public static final double TASA_MUTACION = 0.1;


    public static void Crossover(ArrayList<DataSets> Datos, DataSets padre) {
        Random random = new Random();
        DataSets Hijo1 = padre;
        padre = null;
        int Cross = random.nextInt(101);
        int Mut = random.nextInt(101);

        if (Cross >= 50) {
            System.out.println("\nResultado: " + Cross + " = REALIZAR CROSSOVER");
            // Seleccionar a la madre
            DataSets madre = Roulette.Girar(Datos, Hijo1, Roulette.Porcentaje(Datos));
            DataSets Hijo2 = madre;
            madre = null;

            realizarCrossover(Hijo1, Hijo2);

            if (Mut >= 50) {
                System.out.println("\nResultado: " + Mut + " = REALIZAR MUTACION");
                System.out.println("\nSe mutara el Hijo 1");
                Mutation(Hijo1);
            } else {
                System.out.println("\nResultado: " + Mut + " = NO REALIZAR MUTACION");
                System.out.println("\nSe mutara el Hijo 2");
                Mutation(Hijo2);
            }

            System.out.println("\nNueva Población después de la iteración:");
            for (DataSets dataSet : NuevaPoblacion) {
                System.out.println("B0: " + Arrays.toString(dataSet.B0) + ", B1: " + Arrays.toString(dataSet.B1) + ", Fitness: " + dataSet.fitness);
            }

        } else {
            System.out.println("\nResultado: " + Cross + " = NO REALIZAR CROSSOVER");
            NuevaPoblacion.add(Hijo1);

            System.out.println("\nNueva Población después de la iteración:");
            for (DataSets dataSet : NuevaPoblacion) {
                System.out.println("B0: " + Arrays.toString(dataSet.B0) + ", B1: " + Arrays.toString(dataSet.B1) + ", Fitness: " + dataSet.fitness);
            }
        }
    }

    public static void Mutation(DataSets individuo) {
        Random random = new Random();

        for (int i = 0; i < individuo.B0.length; i++) {
            if (random.nextDouble() < TASA_MUTACION) {
                individuo.B0[i] = 1 - individuo.B0[i];
            }

            if (random.nextDouble() < TASA_MUTACION) {
                individuo.B1[i] = 1 - individuo.B1[i];
            }
        }

        if (NuevaPoblacion.size() != Datos.size()) {
            NuevaPoblacion.add(individuo);
        } else {
            System.out.println("\nLA NUEVA POBLACION ESTA LLENA.");
        }
    }

    public static void realizarCrossover(DataSets padre, DataSets madre) {
        Random random = new Random();
        int longitudGenes = padre.B0.length;

        int puntoCrossover = random.nextInt(longitudGenes);

        for (int i = puntoCrossover; i < longitudGenes; i++) {
            double temp = padre.B0[i];
            padre.B0[i] = madre.B0[i];
            madre.B0[i] = temp;
        }

        for (int i = puntoCrossover; i < longitudGenes; i++) {
            double temp = padre.B1[i];
            padre.B1[i] = madre.B1[i];
            madre.B1[i] = temp;
        }

        double fitnessPadre = DataSets.calcularFitness(Datos, padre.B0[0], padre.B1[0]);
        double fitnessMadre = DataSets.calcularFitness(Datos, madre.B0[0], madre.B1[0]);

        System.out.println("Realizando Crossover en el punto: " + puntoCrossover);
        System.out.println("Hijo 1 después del Crossover - B0: " + Arrays.toString(padre.B0) + ", B1: " + Arrays.toString(padre.B1));
        System.out.println("Fitness: " + fitnessPadre);
        System.out.println("Hijo 2 después del Crossover - B0: " + Arrays.toString(madre.B0) + ", B1: " + Arrays.toString(madre.B1));
        System.out.println("Fitness: " + fitnessMadre);

        int Selec = random.nextInt(101);
        padre.fitness = fitnessPadre;
        madre.fitness = fitnessMadre;

        // Agregar los hijos a la nueva población
        if (Selec >= 101) {

            if (NuevaPoblacion.size() != Datos.size()) {
                NuevaPoblacion.add(padre);
            } else {
                System.out.println("\nLA NUEVA POBLACION ESTA LLENA.");
            }
        } else {

            if (NuevaPoblacion.size() != Datos.size()) {
                NuevaPoblacion.add(madre);
            } else {
                System.out.println("\nLA NUEVA POBLACION ESTA LLENA.");
            }
        }
    }

    public static DataSets seleccionarPadre(ArrayList<DataSets> Datos) {

        double maxFitness = Double.MIN_VALUE;
        DataSets mejorPadre = null;

        for (DataSets dataSet : Datos) {
            double fitness = DataSets.calcularFitness(Datos, dataSet.B0[0], dataSet.B1[0]);

            if (fitness > maxFitness) {
                maxFitness = fitness;
                mejorPadre = dataSet;
            }
        }

        return mejorPadre;
    }
}
