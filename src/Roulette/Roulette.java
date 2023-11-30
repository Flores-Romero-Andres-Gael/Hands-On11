package Roulette;

import DataSets.DataSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Roulette {

    public static DataSets Girar(ArrayList<DataSets> Datos, DataSets padre, int FitnessMax) {
        Random random = new Random();

        double porcentajeAcumulado = 0;
        DataSets cromosomaSeleccionado = null;

        while (cromosomaSeleccionado == null || cromosomaSeleccionado == padre) {
            porcentajeAcumulado = 0;
            double seleccion = random.nextDouble() * 100;

            if (FitnessMax != 0) { // Evitar división por cero
                for (DataSets dataSet : Datos) {
                    porcentajeAcumulado += ((double) dataSet.fitness / FitnessMax) * 100;

                    if (seleccion <= porcentajeAcumulado) {
                        cromosomaSeleccionado = dataSet;
                        break;
                    }
                }
            }
        }

        if (cromosomaSeleccionado != null) {
            System.out.println("Cromosoma seleccionada: x: " + cromosomaSeleccionado.contador + ", B0: " + Arrays.toString(cromosomaSeleccionado.B0) + ", Fitness: " + cromosomaSeleccionado.fitness);
        } else {
            System.out.println("Cromosoma seleccionada: null");
        }

        return cromosomaSeleccionado;
    }

    public static int Porcentaje(ArrayList<DataSets> Datos) {
        int FitnessMax = 0;

        for (DataSets dataSet : Datos) {
            FitnessMax += dataSet.fitness;
        }

        System.out.println("Fitness Total: " + FitnessMax);

        if (FitnessMax != 0) { // Evitar división por cero
            System.out.println("Porcentaje del Fitness: ");

            for (DataSets dataSet : Datos) {
                double porcentaje = ((double) dataSet.fitness / FitnessMax) * 100;
                System.out.println("x: " + dataSet.contador + ", Porcentaje de Fitness: " + porcentaje + "%");
            }
        } else {
            System.out.println("El Fitness Total es 0. No se puede calcular el porcentaje.");
        }

        return FitnessMax;
    }

    public static double CalcularFitness(DataSets dataSet) {
        double suma = 0;
        for (double gene : dataSet.B0) {
            suma += gene;
        }
        return suma;
    }
}
