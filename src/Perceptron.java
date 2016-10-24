import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Homo superior on 2016-10-09.
 */
public class Perceptron {

    private int inputNumbers;                                               // liczba wejść
    private double[] weight;                                                // wektor wag
    private double[] inputSignals;                                          // wektor podany na wejscie wytrenowanego  neuronu
    private double outputSignal;                                            // wartosc odpowiedzi neuronu
    private double outputSignalSuma;                                        // suma odpowiedzi neuronu

    private int howMany = 10000;


    public Perceptron(int inputNumbers) {
        this.inputNumbers = inputNumbers;
        weight = new double[inputNumbers];
        inputSignals = new double[inputNumbers];
    }

    public Perceptron(int inputNumbers, int howMany) {
        this.inputNumbers = inputNumbers;
        weight = new double[inputNumbers];
        inputSignals = new double[inputNumbers];
        this.howMany = howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    @Override
    public String toString() {
        return "Perceptron{" + "weight=" + Arrays.toString(weight);
    }

    public void trainNeutron(double[][] learnData) {

        clearWeights();
        int counter = 0;
        /*
        Set<Integer> set = new HashSet<>();

        int selectNeutron = 0;
        int counter = 0;

        // czy w zbiorze rozwiazan jest tyle elementow co ilość neuronow trenujacych
        while (!(set.size() == learnData.length)) {

            selectNeutron = new Random().nextInt((learnData.length - 1) + 1);

            if (checkAnswer(learnData[selectNeutron])) {
                set.add(selectNeutron);
                //counter++;
            } else {
                set.clear();
                do {
                    //System.out.println("sdadsa");
                    updateWeights(learnData[selectNeutron]);
                    counter++;
                } while (!checkAnswer(learnData[selectNeutron]));
                set.add(selectNeutron);
            }
            */

        while (counter<howMany){

            double blad;
            double SSE=0;
            double suma_bledow=0;
            for (int i = 0; i < learnData.length; i++) {
                updateWeights(learnData[i]);
                blad = learnData[i][inputNumbers-1]-Math.signum(outputSignalSuma);
                //System.out.println(blad);
                suma_bledow+=blad;
                SSE+=Math.pow(blad,2);
            }
            counter++;

            System.out.println("It: "+ counter +" "+ toString()+"  SSE: "+ SSE+ "   MSE: "+ (SSE/learnData.length));
            if(suma_bledow==0)
                break;



        }
        //System.out.println(counter);

    }

    public double neutronAnswer(double[] vecotrIn) {
        setInputs(vecotrIn);
        return outputSignal;
    }

    public boolean checkAnswer(double[] learnVector) {
        setInputs(learnVector);
        //System.out.println("ss");

        if (learnVector[learnVector.length - 1] > 0 && outputSignal > 0) {
            return true;
        } else if (learnVector[learnVector.length - 1] < 0 && outputSignal < 0) {
            return true;
        }
        // Neuron nie udzielil poprawnej odpowiedzi
        else {
            return false;
        }
    }

    private void setInputs(double[] vector) {
        outputSignalSuma = 0;

        for (int i = 0; i < inputNumbers; i++) {
            inputSignals[i] = vector[i];
            outputSignalSuma += inputSignals[i] * weight[i];
        }

        if (outputSignalSuma > 0) {
            outputSignal = 1;
        } else if (outputSignalSuma <= 0) {
            outputSignal = -1;
        }

    }

    private void updateWeights(double[] vector) {


        for (int i = 0; i < inputNumbers; i++) {
            weight[i] += ((vector[vector.length - 1] - Math.signum(outputSignalSuma))) * vector[i];
        }

        //System.out.println(toString());

    }

    private void clearWeights() {
        for (int i = 0; i < inputNumbers; i++) {
            weight[i] = new Random().nextDouble();
        }
    }

}