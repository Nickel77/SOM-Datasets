import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MengerSponge {
    public static void main(String[] args) {
        // Prompt user for configuration of sponge
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the number of iterations: ");
        int n = console.nextInt();
        System.out.print("Enter the number of points: ");
        long startTime = System.nanoTime();
        int numOfPoints = console.nextInt();
        console.close();

        int pointsPrinted = 0;
        ArrayList<Box> sponge = new ArrayList<>((int) Math.pow(20, n));

        // Start with first box
        Box b = new Box(0, 0, 0, 200);
        sponge.add(b);

        for (int iteration = 1; iteration <= n; iteration++) {
            ArrayList<Box> nextBoxes = new ArrayList<>();
            // Loop through each existing box and generate the new boxes
            // from it
            sponge.forEach(box -> {
                List<Box> newBoxes = box.generate();
                nextBoxes.addAll(newBoxes);
            });
            sponge = nextBoxes;
        }

        // Randomize the points in each box and record the amount of points stored
        for (int index = 0; index < sponge.size(); index++) {
            sponge.set(index, sponge.get(index).randomize(numOfPoints / sponge.size()));
            pointsPrinted += (numOfPoints / sponge.size());
        }

        // Correct for the remaining points that were not stored
        correctPoints(sponge, numOfPoints, pointsPrinted);

        // Print output to text files
        printToFile(sponge);

        // Print the time it took to generate the sponge to the console
        System.out.printf("Time taken: %.3f seconds%n", 
            (System.nanoTime() - startTime) / 1e9);
    }

    private static void printToFile(List<Box> sponge) {
        try {
            PrintStream xwriter = new PrintStream("spongex.txt");
            PrintStream ywriter = new PrintStream("spongey.txt");
            PrintStream zwriter = new PrintStream("spongez.txt");

            // Get each point from each box and print it to the text files
            for (Box box : sponge) {
                for (Point p : box.getPoints()) {
                    xwriter.println(p.x());
                    ywriter.println(p.y());
                    zwriter.println(p.z());
                }
            }

            xwriter.close();
            ywriter.close();
            zwriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } finally {
            System.out.println("Printed");
        }
    }

    private static void correctPoints(List<Box> sponge, int numOfPoints, int pointsPrinted) {
        int pointsToAdd = numOfPoints - pointsPrinted;
        // Add one point to each box in the sponge until the number of points is correct
        for (int iteration = 0; iteration < pointsToAdd; iteration++) {
            Box current = sponge.get(iteration);
            current.addPoint(current.getRandomPoint());
        }
    }
}
