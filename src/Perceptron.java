import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    // private double[] inputSignals;                                          // wektor podany na wejscie wytrenowanego  neuronu
    private double outputSignal;                                            // wartosc odpowiedzi neuronu
    //private double outputSignalSuma;                                        // suma odpowiedzi neuronu

    private int howManyMax = 100;
    private double learningRate = 0.1;
    private double bias;
    private double desireError = 0.2;

    public Perceptron(int inputNumbers) {
        this.inputNumbers = inputNumbers;
        weight = new double[inputNumbers];
    }


    public void setHowMany(int howMany) {
        this.howManyMax = howMany;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setDesireError(double desireError) {
        this.desireError = desireError;
    }

    @Override
    public String toString() {
        return "Perceptron{" + "weight=" + Arrays.toString(weight);
    }

    public void trainNeutron(double[][] learnData, double[][] validData) throws FileNotFoundException {

        clearWeights();
        System.out.println("ITERATION START");
        int counter = 0;
        double globalError, MAPE;                   //globalError to MSE
        double error = 0.0;
        PrintWriter writeTraining = new PrintWriter("training.txt");
        writeTraining.println("Epoch: \tMSE: \tMAPE: ");
        PrintWriter writeVerify = new PrintWriter("verifying.txt");
        writeVerify.println("Epoch: \tMSE: \tMAPE: \tUnder: \tOver");
        do {
            counter++;
            double output;
            globalError = 0.0;
            MAPE = 0.0;

            for (double[] aLearnData : learnData) {
                output = neutronAnswer(aLearnData);
                error = aLearnData[inputNumbers] - output;
                if (error != 0.0) {
                    updateWeights(aLearnData, error);
                    globalError += error * error;
                    MAPE += Math.abs(error / neutronAnswer(aLearnData));
                }
            }
            MAPE = MAPE * 100 / learnData.length;
            //wypisanie wszystkiego
            System.out.println("Epoch: " + counter + " MSE: " + (globalError / learnData.length) + " MAPE: " + MAPE);
            writeTraining.println(counter + "\t" + (globalError / learnData.length) + "\t" + MAPE);
            System.out.println(toString());

            double globalErrorValid = 0.0;
            double MAPEvalid = 0.0;
            int under = 0, over = 0;

            for (double[] aValidData : validData) {
                output = neutronAnswer(aValidData);
                error = aValidData[inputNumbers] - output;
                if (error != 0.0) {
                    if (output > aValidData[inputNumbers])
                        over++;
                    else
                        under++;
                    globalErrorValid += error * error;
                    MAPEvalid += Math.abs(error / neutronAnswer(aValidData));
                }
            }

            MAPEvalid = MAPEvalid * 100 / validData.length;
            System.out.println("Validating");
            System.out.println("Epoch: " + counter + " MSE: " + (globalErrorValid / validData.length) + " MAPE: " + MAPEvalid);
            System.out.println("Under: " + under + " Over: " + over+"\n\n");
            writeVerify.println(counter + "\t" + (globalErrorValid / validData.length) + "\t" + MAPEvalid+"\t" + under + "\t" + over);
        }
        while (counter < howManyMax && globalError > desireError);
        //System.out.println(counter);
        writeTraining.close();
        writeVerify.close();
    }

    public double neutronAnswer(double[] vecotrIn) {
        setInputs(vecotrIn);
        return outputSignal;
    }


    private void setInputs(double[] vector) {
        double outputSignalSuma = bias;
        for (int i = 0; i < inputNumbers; i++) {
            outputSignalSuma += vector[i] * weight[i];
        }
        outputSignal = Math.signum(outputSignalSuma);

    }

    private void updateWeights(double[] vector, double error) {
        for (int i = 0; i < inputNumbers; i++) {
            weight[i] += learningRate * error * vector[i];
        }
        bias += learningRate * error;
    }

    private void clearWeights() {
        for (int i = 0; i < inputNumbers; i++) {
            weight[i] = new Random().nextDouble();
        }
        bias = new Random().nextDouble();
    }


}