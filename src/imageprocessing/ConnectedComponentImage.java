package imageprocessing;

import java.awt.Color;
import edu.princeton.cs.introcs.Picture;
import imageprocessing.Luminance;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;


/*************************************************************************
 * Compilation: java ConnectedComponentImage.java
 * 
 * The <tt>ConnectedComponentImage</tt> class
 * <p>
 * This class will take in an image file, and apply
 * the union find algorithm to the picture. The amount
 * of components in the image is then returned to
 * the user aswell as a binarised version of the image,
 * colored version of the picture and a picture with
 * all the objects surrounded with a red box.
 * 
 * @author David Walsh
 *************************************************************************/
public class ConnectedComponentImage {
	private int width,height,count;
	private int[] id,sz;
	private ArrayList<Integer> roots;
	private ArrayList<Color> colors;


	private static String fileLocation;


	/**
	 * Initialise fields
	 * 
	 * @param fileLocation
	 */
	public ConnectedComponentImage(String fileLocation) {
		this.fileLocation = fileLocation;
		roots = new ArrayList<>();
		colors = new ArrayList<>();
		
		Picture pic = binaryComponentImage();
		width = pic.width();
		height = pic.height();
		int N = width * height;

		weightedQuickFind(N);
		WeightedQU();
		

	/**
	 * The main method which makes an new instance of the Connected
	 * ComponentImage class, with the image bacteria.bmp
	 * 
	 * @return the number of components (between 1 and N)
	 */
		}
		public static void main(String[] args) {
	
			ConnectedComponentImage app = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/bacteria.bmp");
		}
	
	
		
	
	
	
	
	/**
	 * Returns the number of components identified in the image.
	 * 
	 * @return the number of components (between 1 and N)
	 */
		public int countComponents() {
			return count;
		}

	/**
	 * Returns the original image with each object bounded by a red box.
	 * 
	 * @return a picture object with all components surrounded by a red box
	 */
		public Picture  identifiedComponentImage() {
	
			Picture boxPic = new Picture(fileLocation);
			findRootsArray(boxPic);
			populateColorArrays();
			Collections.sort(roots);
	
			for(int i = 0; i < roots.size(); i++)	{
				int maxX = 0;
				int minX = boxPic.width();
				int maxY = 0;
				int minY = boxPic.height();
	
				for (int x = 0; x < boxPic.width(); x++) {
					for (int y = 0; y < boxPic.height(); y++) {
	
						int currentRoot = root(id[y*width+x]);
						if (currentRoot == roots.get(i)) {
	
							if (x < minX)
								minX = x;
							if (x > maxX)
								maxX = x;
							if (y < minY)
								minY = y;
							if (y > maxY)
								maxY = y;
						}
					}
				}
	
				if (minX > maxX || minY > maxY) {
					System.out.println("It's All White Pixels!!!");
				} else {
					for (int x = minX; x <= maxX; x++) {
						boxPic.set(x, minY, Color.RED);
						boxPic.set(x, maxY, Color.RED);
					}
	
					for (int y = minY; y <= maxY; y++) {
						boxPic.set(minX, y, Color.RED);
						boxPic.set(maxX, y, Color.RED);
					}
				}
			}
			return boxPic;
		}
	
	/**
	 * Returns a picture with each object updated to a random colour.
	 * @return 
	 * 
	 * @return a picture object with all components coloured.
	 */
		public Picture colourComponentImage() {
			Picture pic = new Picture(binaryComponentImage());
	
			findRootsArray(pic);
			populateColorArrays();
			Collections.sort(roots);
	
			for (int x = 0; x < pic.width(); x++) {
				for (int y = 0; y < pic.height(); y++) {
					for(int i = 0; i < roots.size(); i++)	{
	
						int currentRoot = root(id[y*width+x]);
	
						if (currentRoot == roots.get(i))
							pic.set(x, y, colors.get(i));
					}
				}
			}
			return pic;
		}

	/**
	 * Returns the original image.
	 * 
	 * @return a picture object which has been created based on the 
	 * fileLocation.
	 */
		public Picture getPicture() {
			Picture pic = new Picture(fileLocation);
			return pic;
		}
		
	/**
	* This method takes in a component picture and cross checks 
	* it to find the amount of roots and populate a roots array
	* with the values of the roots.
	 */
		
		public void findRootsArray(Picture pic){
			for (int x = 0; x < pic.width(); x++) {
				for (int y = 0; y < pic.height(); y++) {
					int currentNumber = find(y*width+x);
					if(x==0 && y==0){
						roots.add(currentNumber);
					}
					if(!roots.contains(currentNumber)){
						roots.add(currentNumber);
					}
				}
			}
	
		}
	/**
	 * Populates the pixel array with random color objects
	 * based on the size of the roots array.
	 * 
	 */

		public void populateColorArrays(){
			Random rand = new Random();	
			for(int i=0; i<=roots.size() ; i++){
	
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				Color randomColor = new Color(r, g, b);
				colors.add(randomColor);
			}
		}
	/**
	 * Returns a binarised version of the original image
	 * 
	 * @return a picture object with all pixels either black
	 * or white based on where they lie in the lumincance 
	 * threshold.
	 */
		public Picture binaryComponentImage() {
	
			Picture pic = new Picture(fileLocation);	
			int width = pic.width();
			int height = pic.height();
			double thresholdPixelValue = 128.0;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Color c = pic.get(x, y);
					if (Luminance.lum(c) < thresholdPixelValue) {
						pic.set(x, y, Color.BLACK);
					} else {
						pic.set(x, y, Color.WHITE);
					}
				}
			}
			return pic;


	}
	/**
	 * This method applies Weighted Quick Union by tesing the current pixel in a 8
	 * neighbourhood search to see if they have the same color, if they do they are
	 * unioned. 
	 * 
	 */
		public void WeightedQU()
		{
			Picture pic = binaryComponentImage();
	
			for (int y = 0; y < pic.height(); y++) {   
				for (int x = 0; x < pic.width(); x++) {
	
					Color a,b,c,d,e,f,g,h;//creating a color for each side
	
					if(x > 0){//North Pixel
						a = pic.get(x-1, y);
					}else{
						a = pic.get(x, y);
					}
	
					if(y!= (height-1) ){//East Pixel
						b = pic.get(x, y+1);
					}else{
						b = pic.get(x, y);
					}
	
					if((y-1) >= 0){//West Pixel
						e = pic.get(x, y-1);
					}else{
						e = pic.get(x, y);
					}
	
	
					if(x!=width-1 && y!=height-1){//South East Pixel
						c = pic.get(x+1, y+1);
					}else{
						c=pic.get(x, y); 
					}
					//
					if(x!=width-1){//South Pixel
						d = pic.get(x+1, y);
					}else{
	
						d = pic.get(x, y);
					} 
	
	
					if((x-1) >= 0 && (x-1) <= width && (y+1) <= height-1 ){//North East Pixel
						f = pic.get(x-1, y+1);
					}else{
	
						f = pic.get(x, y);
					}
	
					if(x!=width-1 && (y-1)>=0){//South West Pixel
						g = pic.get(x+1, y-1);
					}else{
	
						g = pic.get(x, y);
					}
	
					if((x-1) >= 0 && (y-1)>=0){//North West Pixel
						h = pic.get(x-1, y-1);
					}else{
	
						h = pic.get(x, y);
					}
	
					Color current = pic.get(x, y);
					double lumA = Luminance.lum(a);//NORTH
					double lumB = Luminance.lum(b);//EAST
					double lumC = Luminance.lum(c);//SOUTH EAST
					double lumD = Luminance.lum(d);//SOUTH
					double lumE = Luminance.lum(e);//WEST
					double lumF = Luminance.lum(f);//NORTHEAST
					double lumG = Luminance.lum(g);//SouthWest
					double lumH = Luminance.lum(h);//NorthWest
					double lumCurrent = Luminance.lum(current);
	
					//This will check again if it within the boundry if not and it has been given the value of the one doing the check ie. 
					//b!=current. It checks if they have the same RGB code by lumB == lumCurrent and whether or not they are already connected.
					//If it passes all these checks it will be unioned.
	
					if (y!= (height-1) && b!=current && lumB == lumCurrent && connected(id[y*width+x],id[(y+1)*width+x]) == false) 
						union(id[y*width+x],id[(y+1)*width+x]);//East Pixel
	
					if ((x!=width-1) && d!=current && (lumD == lumCurrent) && (connected(id[y*width+x],id[y*width+x+1])) == false) 
						union(id[y*width+x],id[y*width+x+1]);//South Pixel
	
	
					if (((x!=width-1 && y!=height-1)) && c!=current && (lumC == lumCurrent) && (connected(id[(y+1)*width+(x+1)],id[y*width+x])) == false)
						union(id[(y+1)*width+x+1],id[y*width+x]);//South Pixel
	
					if ((y-1 >= 0) && e!=current && (lumE == lumCurrent) && (connected(id[(y-1)*width+x],id[y*width+x])) == false) 
						union(id[(y-1)*width+x],id[y*width+x]);//West Pixel
	
					if ((x-1 >= 0) && a!=current && (lumA == lumCurrent) && (connected(id[y*width+x-1],id[y*width+x])) == false) 
						union(id[y*width+x-1],id[y*width+x]);//North Pixel
	
					if ((x-1 >= 0 && y!=height-1) && f!=current && (lumF == lumCurrent) && (connected(id[(y+1)*width+x-1],id[y*width+x])) == false) 
						union(id[(y+1)*width+x-1],id[y*width+x]);//NorthEast Pixel
	
					if ((x!=width-1 && y-1 >= 0) && g!=current && (lumG == lumCurrent) && (connected(id[(y-1)*width+x+1],id[y*width+x])) == false) 
						union(id[(y-1)*width+x+1],id[y*width+x]);//South West Pixel
	
					if (((x-1) >= 0 && y-1 >= 0) && h!=current && (lumH == lumCurrent) && (connected(id[(y-1)*width+x-1],id[y*width+x])) == false) 
						union(id[(y-1)*width+x-1],id[y*width+x]);//North West Pixel
	
				}
	
			}
		}

	/**
	 * This method takes in two ints @param p and @param q,
	 * finds them in the id array and unions them.
	 * count gets decremented by 1 for every union. 		
	 * 
	 */
		public void union(int p, int q) {
	
	
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return;
	
			// make smaller root point to larger one
			if (sz[rootP] < sz[rootQ]) {
				id[rootP] = rootQ;
				sz[rootQ] += sz[rootP];
			}
			else {
				id[rootQ] = rootP;
				sz[rootP] += sz[rootQ];
			}
			count--;
		}

	/**
	 * This method will return the root of the
	 * @param i int that is passed through it.
	 * 
	 * @return i which is the root.
	 *
	 */
		public int root(int i) {
			while (i != id[i]) i = id[i];
			return i;
		} 

	/**
	 * This method @return count to the user.
	 */
		public int count() {
			return count;
		}

	/**
	 * This method checks if two elements are
	 * already connected.
	 * @return root(p) == root(q)
	 */
		public boolean connected(int p, int q) {
	
			return root(p) == root(q);		
		}
	
	/**
	 * This method populate the id and sz array
	 * based in the size of the picture.
	 * @param int N = pic.width*pic.height
	 */

		public void weightedQuickFind(int N) {
			id = new int[N];
			sz = new int[N];
			count = N;
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i] = 1;
			}
		}


	/**
	 * This method will search to see if the
	 * 
	 * @param p
	 * is a valid entry and if so it will @return p.
	 */
		public int find(int p) {
			validate(p);
			while (p != id[p])
				p = id[p];
			return p;
		}


	/**
	 * This method will validate 
	 * if the @param int p falls within
	 * the range based on the size of the id[] array.
	 */
		private void validate(int p) {
			int N = id.length;
			if (p < 0 || p >= N) {
				throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N - 1));
			}
		}
}
