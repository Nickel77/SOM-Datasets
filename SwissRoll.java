import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;

public class SwissRoll {
    public static void main(String[] args) throws FileNotFoundException {
        // Create a Swiss Roll with 4096 points
        makeSwissRoll(4096);
    }

    // Make a swiss roll with numPoints number of points and write each x, y, and z
    // coordinate to 3 files
    private static void makeSwissRoll(int numPoints) throws FileNotFoundException {

        // Make file objects and printstreams to write to files for each dimension
        File zfile = new File("G:\\SwissRollJavaCode\\zPoints.txt");
        File xfile = new File("G:\\SwissRollJavaCode\\xPoints.txt");
        File yfile = new File("G:\\SwissRollJavaCode\\yPoints.txt");
        PrintStream writerx = new PrintStream(xfile);
        PrintStream writery = new PrintStream(yfile);
        PrintStream writerz = new PrintStream(zfile);

        // Get random values for theta with a domain of [0, 4pi].
        // This can be increased or decreased to the desired range.
        double[] theta = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            // Generate a random number between 0 and 4pi
            theta[i] = Math.random() * 4 * Math.PI;
        }

        // Use the polar function r = 1.1^theta - 1 (Swiss Roll polar function)
        // to get the r values from theta
        double[] r = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            r[i] = Math.pow(1.1, theta[i]) - 1;
        }

        // Convert polar to cartesian coordinates to be stored in these arrays
        double[] x = polarToCartesian(theta, r, true);
        double[] y = polarToCartesian(theta, r, false);

        // Random Z values from 0 to 10 for each point which will be along
        // the line made by the x and y points
        double[] z = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            z[i] = Math.random() * 10;
        }

        // Print the x, y, and z values rounded to 7 decimal places to files
        // using the prior printstreams objects
        for (int rows = 0; rows < numPoints; rows++) {
            writerx.print((x[rows] + "").format("%.7f", x[rows]));
            writery.print((y[rows] + "").format("%.7f", y[rows]));
            writerz.print((z[rows] + "").format("%.7f", z[rows]));
            writerx.println();
            writery.println();
            writerz.println();
        }

        // If a 2d array is required then the following method can be used
        // instead of the code above
        // print2DArray(x, y, z);

        writerx.close();
        writery.close();
        writerz.close();
        System.out.println("Printed");


        
        
    }

    // Optional code used to print a 2d array of points if desired
    private static void print2DArray(double[] x, double[] y, double[] z) throws FileNotFoundException {
        int numPoints = x.length;
        Double[][] points = new Double[numPoints][3];
        for (int i = 0; i < numPoints; i++) {
            points[i][0] = x[i];
            points[i][1] = y[i];
            points[i][2] = z[i];
        } 
        
        // Print the points to a file with PrintStream rounded to 7 decimal places
        File pointsFile = new File("G:\\SwissRollJavaCode\\points.txt");
        PrintStream writer = new PrintStream(pointsFile);
        for (int rows = 0; rows < numPoints; rows++) {
            for(int cols = 0; cols < 3; cols++) {
                writer.print((points[rows][cols] + "").format("%.7f", points[rows][cols]));
                if (cols != 2) {
                writer.print(", ");
                }
            }
            writer.println();
        }

        writer.close();
    }

    // Convert polar coordinates to cartesian coordinates for plotting easily
    private static double[] polarToCartesian(double[] theta, double[] r, boolean isX) {
        double[] coordinate = new double[theta.length];
        if (isX) {
            for (int i = 0; i < theta.length; i++) {
                coordinate[i] = r[i] * Math.cos(theta[i]);
            }
        } else {
            for (int i = 0; i < theta.length; i++) {
                coordinate[i] = r[i] * Math.sin(theta[i]);
            }
        }

        return coordinate;
    }
}