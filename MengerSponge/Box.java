import java.util.ArrayList;
import java.util.List;

public class Box {
	private Point pos;
	private double size;
	private List<Point> points;

	public Box(double x, double y, double z, double size) {
		pos = new Point(x, y, z);
		this.size = size;
	}

	public Box(double x, double y, double z, double size, List<Point> points) {
		pos = new Point(x, y, z);
		this.size = size;
		this.points = points;
	}

	ArrayList<Box> generate() {
		ArrayList<Box> boxes = new ArrayList<>(8);
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				for (int z = -1; z < 2; z++) {
					int sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
					double newSize = size / 3;
					if (sum > 1) {
						Box b = new Box(pos.x() + x * newSize, pos.y() + y * newSize, pos.z() + z * newSize, newSize);
						boxes.add(b);
					}
				}
			}
		}
		return boxes;
	}

	public Box randomize(int numOfPoints) {
		ArrayList<Point> randomPoints = new ArrayList<>(numOfPoints);
		for (int i = 0; i < numOfPoints; i++) {
			// Add random point in the range of the box to the list
			randomPoints.add(getRandomPoint());
		}

		/*ArrayList<Point> uniquePoints = new ArrayList<>(numOfPoints);
		for (int index = 0; index < randomPoints.size(); index++) {
			if (!uniquePoints.contains(randomPoints.get(index))) {
				uniquePoints.add(randomPoints.get(index));
			}
		} */

		return new Box(pos.x(), pos.y(), pos.z(), size, randomPoints);
	}

	public Point getRandomPoint() {
		// Get the min and max of the random point using the position and size
		double halfSize = size / 2.0;
		double[] max = { pos.x() + halfSize, pos.y() + halfSize, pos.z() + halfSize };
		double[] min = { pos.x() - halfSize, pos.y() - halfSize, pos.z() - halfSize };
		// Use Math.random() to get a random point within the range of the box
		double randomX = Math.random() * (max[0] - min[0]) + min[0];
		double randomY = Math.random() * (max[1] - min[1]) + min[1];
		double randomZ = Math.random() * (max[2] - min[2]) + min[2];
		return new Point(randomX, randomY, randomZ);
	}

	public void addPoint(Point p) {
		points.add(p);
	}

	public Point getCenter() {
		return pos;
	}

	public double getSize() {
		return size;
	}

	public List<Point> getPoints() {
		return points;
	}
}