import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SwissRoll {
    public static void main(String[] args) {
        // Create a Swiss Roll with a user specified number of points
        int numPoints = getPoints();
        makeSwissRoll(numPoints);
    }

    // Make a swiss roll with numPoints number of points and write each x, y, and z
    // coordinate to 3 files
    private static void makeSwissRoll(int numPoints) {
        // Get random values for theta with a domain of [0, 4pi].
        // This can be increased or decreased to the desired range.
        double[] theta = new double[numPoints];
        for (int i = 0; i < numPoints; i++)
            theta[i] = Math.random() * 4 * Math.PI;

        // Use the polar function r = 1.001^theta - 1 (Swiss Roll polar function)
        // to get the r values from theta
        double[] r = new double[numPoints];
        for (int i = 0; i < numPoints; i++)
            r[i] = Math.pow(1.001, theta[i]) - 1;

        // Convert polar to cartesian coordinates to be stored in these arrays
        double[] x = polarToCartesian(theta, r, true);
        double[] y = polarToCartesian(theta, r, false);

        // Random Z values from 0 to 10 for each point which will be along
        // the line made by the x and y points
        double[] z = new double[numPoints];
        for (int i = 0; i < numPoints; i++)
            z[i] = Math.random() * 10;

        // Print the x, y, and z arrays to the files
        printToFiles(x, y, z);

        // If a 2d array is required then the following method can be used
        // instead of the method call above
        // print2DArray(x, y, z);
    }

    private static void printToFiles(double[] x, double[] y, double[] z) {
        try {
            // Make Printstreams to write to files for each dimension
            PrintStream writerx = new PrintStream("xPoints.txt");
            PrintStream writery = new PrintStream("yPoints.txt");
            PrintStream writerz = new PrintStream("zPoints.txt");

            // Print the x, y, and z values rounded to 7 decimal places to files
            // using the prior printstreams objects
            for (int rows = 0; rows < x.length; rows++) {
                writerx.println(String.format("%.7f", x[rows]));
                writery.println(String.format("%.7f", y[rows]));
                writerz.println(String.format("%.7f", z[rows]));
            }

            writerx.close();
            writery.close();
            writerz.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } finally {
            System.out.println("Printed");
        }
    }

    private static int getPoints() {
        Scanner input = new Scanner(System.in);
        int n = 0;
        System.out.print("Enter the number of points: ");
        try {
            n = input.nextInt();
            input.close();
        } catch (InputMismatchException e) {
            // If the user enters a non-integer value, the program will
            // prompt the user to enter a valid integer value and 
            // re-enter main() to run the program again.
            System.out.println("Invalid input, please try again.");
            main(new String[] {});
            System.exit(0);
        }
        
        return n;     
    }

    // Optional code used to print a 2d array of points if desired
    private static void print2DArray(double[] x, double[] y, double[] z) {
        int numPoints = x.length;
        double[][] points = new double[numPoints][3];
        for (int i = 0; i < numPoints; i++) {
            points[i][0] = x[i];
            points[i][1] = y[i];
            points[i][2] = z[i];
        } 
        
        try {
            // Print the points to a file with PrintStream rounded to 7 decimal places
            PrintStream writer = new PrintStream("points.txt");
            for (int rows = 0; rows < numPoints; rows++) {
                for (int cols = 0; cols < 3; cols++) {
                    writer.print(String.format("%.7f", points[rows][cols]));
                    if (cols != 2) 
                        writer.print(", ");

                }

                writer.println();
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } finally {
            System.out.println("Printed");
        }
    }

    // Convert polar coordinates to cartesian coordinates for plotting easily
    private static double[] polarToCartesian(double[] theta, double[] r, boolean isX) {
        double[] coordinate = new double[theta.length];
        if (isX)
            for (int i = 0; i < theta.length; i++)
                coordinate[i] = r[i] * Math.cos(theta[i]);
        else
            for (int i = 0; i < theta.length; i++)
                coordinate[i] = r[i] * Math.sin(theta[i]);
    
        return coordinate;
    }
}
