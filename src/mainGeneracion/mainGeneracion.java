package mainGeneracion;

import Cromosomas.Cromosomas;
import DataSets.DataSets;

import java.util.ArrayList;
import java.util.Arrays;

public class mainGeneracion {

    public static ArrayList<DataSets> Datos = new ArrayList<>(DataSets.DatosPoblacionInicial());

    public static void main(String[] args) {
        Funtion(Datos);
    }


    public static void Funtion(ArrayList<DataSets> Datos) {

        while (true) {
            System.out.println("\nIteración:");

            for (DataSets dataSet : Datos) {
                double fitness = DataSets.calcularFitness(Datos, dataSet.B0[0], dataSet.B1[0]);
                System.out.println("x: " + dataSet.contador + ", B0: " + Arrays.toString(dataSet.B0) + ", B1: " + Arrays.toString(dataSet.B1) + ", Fitness: " + fitness);
            }

            DataSets padre = Cromosomas.seleccionarPadre(Datos);
            double fitnessPadre = DataSets.calcularFitness(Datos, padre.B0[0], padre.B1[0]);

            System.out.println("Mejor Padre:\nB0: " + Arrays.toString(padre.B0) + ", B1: " + Arrays.toString(padre.B1) + ", Fitness: " + fitnessPadre);

            Cromosomas.Crossover(Datos, padre);

            // Verificar si el nuevo fitness es cercano a 1
            if (fitnessPadre >= 0.9) {
                System.out.println("Se encontró un fitness cercano a 1. Terminando el programa.");
                break;
            } else {
                System.out.println("Fitness del mejor padre: " + fitnessPadre + ". Continuando la iteración.");
            }

            // Reemplazar la población actual con la nueva población
            Datos.clear();
            Datos.addAll(Cromosomas.NuevaPoblacion);
            Cromosomas.NuevaPoblacion.clear();
        }
    }

}