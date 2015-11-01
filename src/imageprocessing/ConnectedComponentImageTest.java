package imageprocessing;

import static org.junit.Assert.*;

import java.awt.Color;

import imageprocessing.Luminance;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.introcs.Picture;


public class ConnectedComponentImageTest {
	private static ConnectedComponentImage test;
	static Picture pic;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		 test = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/crosses.gif");
		 pic = new Picture("C:/Users/David/workspace/ConnectorStarter/images/crosses.gif") ;
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountComponents() {
		
		test.WeightedQU();
		int actual = test.countComponents();
		assertEquals(9,actual,0);
		
	}

	@Test
	public void testGetPicture() {
		Picture expected = new Picture("C:/Users/David/workspace/ConnectorStarter/images/crosses.gif");
		Picture notExpected = new Picture("C:/Users/David/workspace/ConnectorStarter/images/stars.jpg");
		Picture actual = test.getPicture();
		assertEquals(expected,actual);
		
	}

	@Test
	public void testBinaryComponentImage() {
		Picture binaryPic = test.binaryComponentImage();	
		Color w = Color.WHITE;
		Color b = Color.BLACK;
		int size = (binaryPic.width() * binaryPic.height());
		int expected = 0;
		for (int x = 0; x < binaryPic.width(); x++) {
			for (int y = 0; y < pic.height(); y++) {
				if(pic.get(x, y) == w || pic.get(x, y) == b);{
					expected++;
				}
			}
		}
		assertEquals(size,expected);
	}
			

	
	@Test
	public void testUnion() {
		    test.union(0, 1);
	        test.union(1, 2);
	        test.union(2, 3);
	        test.union(3, 4);
	        
	        assertEquals(test.root(0), test.root(4));
	}

	@Test
	public void testColourComponentImage(){
		Picture pic = test.getPicture();
		Picture colors = test.colourComponentImage();
		int size = (pic.width() * pic.height());
		int expected = 0;
		Color original,color;
		for (int x = 0; x < pic.width(); x++) {
			for (int y = 0; y < pic.height(); y++) {
				
				original = pic.get(x,y);
				color = colors.get(x, y);
				if (original != color){
					 expected++;
				 }
		 
			}
		}
		assertEquals(size,expected);
			
	}
	@Test
	public void testConnected() {
		 //test sites not connected
	    for (int i = 1; i < 20; i++) {
	            assertFalse(test.connected(0, i));
	        }

	    //test connections
	    test.union(0, 1);
	    test.union(9, 8);
	    test.union(15, 7);
	        assertTrue(test.connected(1, 0));
	        assertTrue(test.connected(9, 8));
	        assertTrue(test.connected(15, 7));

	        test.union(8, 1);
	        test.union(7, 1);
	        assertTrue(test.connected(0, 9));
	        assertTrue(test.connected(15,8 ));
	}

	@Test
	public void testWeightedQuickFindUF() {
		 test.weightedQuickFind(20);
		  for (int i = 0; i < 20; i++) assertSame(i, test.find(i));;
	}

	@Test
	public void testFind() {
		
		assertEquals(0,test.find(0));
		
		
		
		
	}

}
