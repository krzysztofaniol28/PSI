import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Homo superior on 2016-10-09.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("data.txt"));
        int howMany = in.nextInt();
        int insert = in.nextInt();
        double data[][] = new double[howMany][insert+1];
        for(int i=0;i<howMany;i++){
            for(int j=0;j<insert+1;j++){
                data[i][j]= in.nextInt();
            }
        }
        in.close();
        //int howMany,insert;
        Scanner in2 = new Scanner(new File("verify.txt"));
        howMany = in2.nextInt();
        insert=in2.nextInt();
        double verify[][] = new double[howMany][insert+1];
        for(int i=0;i<howMany;i++){
            for(int j=0;j<insert+1;j++){
                verify[i][j]=in2.nextInt();
            }
        }
        in2.close();

        Perceptron p = new Perceptron(insert);
        p.trainNeutron(data,verify);

        /*
        System.out.println("\n\n");
        Perceptron x = new Perceptron(3);
        double or2[][]={{-1,-1,-1}, {1,-1,1},{-1,1,1}, {1,1,1}};
        double and[][]={{1,-1,-1,-1},{1,-1,1,-1},{1,1,-1,1},{1,1,1,1},{-1,-1,1,-1}};
        //x.trainNeutron(and,and);
        */
    }
}
