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

        //Set<Integer> set = new HashSet<>();

        int selectNeutron = 0;
        int counter = 0;

        // czy w zbiorze rozwiazan jest tyle elementow co ilość neuronow trenujacych
        while (true) {

            //selectNeutron = new Random().nextInt((learnData.length - 1) + 1);
            /*
            if (checkAnswer(learnData[selectNeutron])) {

                set.add(selectNeutron);
                counter++;

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
            for (int i = 0; i < learnData.length; i++)
                updateWeights(learnData[selectNeutron]);
            counter++;


            if (counter >= howMany)
                break;


        }
        System.out.println(counter);

    }

    public double neutronAnswer(double[] vecotrIn) {
        setInputs(vecotrIn);
        return outputSignal;
    }

    public boolean checkAnswer(double[] learnVector) {
        setInputs(learnVector);


        //  neuron udzielil odpowiedzi zgodnie z wartoscia zawarta w wekorze trenujacym

        if (learnVector[learnVector.length - 1] > 0 && outputSignal > 0) {
            return true;
        } else if (learnVector[learnVector.length - 1] < 0 && outputSignal < 0) {
            return true;
        } else if (learnVector[learnVector.length - 1] == 0 && outputSignal == 0) {
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
        } else if (outputSignalSuma < 0) {
            outputSignal = -1;
        } else {
            outputSignal = 0.0;
        }

    }

    private void updateWeights(double[] vector) {

        if (outputSignal == 0.0) {
            for (int i = 0; i < inputNumbers; i++) {
                weight[i] += vector[vector.length - 1] * vector[i];
            }
        } else {
            for (int i = 0; i < inputNumbers; i++) {
                weight[i] += ((vector[vector.length - 1] - Math.signum(outputSignalSuma)) / 2) * vector[i];
            }
        }

    }

    private void clearWeights() {
        for (int i = 0; i < inputNumbers; i++) {
            weight[i] = new Random().nextDouble();
        }
    }

}
