import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
    	int num = 0;
    	for (Point pt : s.getPoints()) {
    		num += 1;
    	}
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
    	double avgLength = getPerimeter(s) / getNumPoints(s);
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
    	double largest = 0.0;
    	Point prevPt = s.getLastPoint();
    	for (Point currPt : s.getPoints()) {
    		double currDist = prevPt.distance(currPt);
    		if (currDist > largest) {
    			largest = currDist;
    		}
    		prevPt = currPt;
    	}
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
    	double largestX = 0.0;
    	for (Point currPt : s.getPoints()) {
    		if (currPt.getX() > largestX)
    			largestX = currPt.getX();
    	}
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
    	double largestPerimeter = 0.0;
    	DirectoryResource dr = new DirectoryResource();
    	for (File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    		double perimeter = getPerimeter(s);
    		if (perimeter > largestPerimeter)
    			largestPerimeter = perimeter;
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
        	FileResource fr = new FileResource(f);
        	Shape s = new Shape(fr);
        	double perimeter = getPerimeter(s);
        	if (perimeter > largestPerimeter) {
        		largestPerimeter = perimeter;
        		temp = f;
        	}
        }
        	
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("example4.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int num_points = getNumPoints(s);
        System.out.println("number of points = " + num_points);
        double avg_length = getAverageLength(s);
        System.out.println("average length = " + avg_length);
        double largest_side = getLargestSide(s);
        System.out.println("largest side length = " + largest_side);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    	double largestPerimeter = getLargestPerimeterMultipleFiles();
    	System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    	String filename = getFileWithLargestPerimeter();
    	System.out.println("filename with largest perimeter = " + filename);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testFileWithLargestPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
    }
}
