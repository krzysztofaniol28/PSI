/**
 * Created by Homo superior on 2016-10-09.
 */
public class Main {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(2);
        p.setHowMany(20);



        double[][] and = {{1,-1,-1,-1},{1,-1,1,-1},{1,1,-1,-1},{1,1,1,1}};
        double[][] or = {{-1,1,1},{1,1,1},{-1,-1,-1},{1,-1,1}};
        double[][] date = {{-1, -1, -1}};
        // System.out.println(p.toString());

        p.trainNeutron(or);
        System.out.println(p.toString());
        System.out.println(p.checkAnswer(date[0]));

    }
}
