/**
 * Created by Homo superior on 2016-10-09.
 */
public class Main {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(3);
        p.setHowMany(10000);

        double[][] and = {{1, -1, -1, -1}, {1, -1, 1, -1}, {-1, 1, 1, -1}, {1, 1, 1, 1}};
        double[][] date = {{-1, -1, -1, -1}};
       // System.out.println(p.toString());

        p.trainNeutron(and);
        System.out.println(p.toString());
        System.out.println(p.checkAnswer(date[0]));

    }
}
