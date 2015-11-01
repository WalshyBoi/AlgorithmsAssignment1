package imageprocessing;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;

/*************************************************************************
 * Compilation: java gui.java
 * 
 * The <tt>gui</tt> class
 * <p>
 * This class create an instance of the ConnnectedComponentImage
 * class, and provide the user with a GUI for controlling the
 * class.
 * 
 * @author David Walsh
 *************************************************************************/
public class gui {

	private JFrame frame;
	private JTextField txtPicture;
	private ConnectedComponentImage image;
	private JLabel picBoundry;
	private BufferedImage img;
	private JTextField txtClickBelowTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					gui window = new gui();
					window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
		image = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/bacteria.bmp");
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	private void initialize()  {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Creates the return count button
		JButton returnCount = new JButton("Return Count");
		returnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.WeightedQU();
				JOptionPane.showMessageDialog(null, "There are " +(image.countComponents()-1) +" objects in the image, and 1 background component ");
			}
		});
		returnCount.setBounds(10, 108, 114, 23);
		frame.getContentPane().add(returnCount);
		
		//creates the show binary button
		JButton binary = new JButton("Show Binary");
		binary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				image.binaryComponentImage().show();; 
				
				
			}
		});
		binary.setBounds(10, 142, 114, 23);
		frame.getContentPane().add(binary);
		
		
		//Creates the color image button.
		JButton colourImage = new JButton("Colour Image");
		colourImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.colourComponentImage().show();
			}
		});
		colourImage.setBounds(10, 210, 114, 23);
		frame.getContentPane().add(colourImage);
		
		
		//Creates the boundry button
		JButton boundry = new JButton("Boundry Box");
		boundry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.identifiedComponentImage().show();
			}
		});
		boundry.setBounds(10, 176, 114, 23);
		frame.getContentPane().add(boundry);
		
		txtPicture = new JTextField();
		txtPicture.setForeground(Color.WHITE);
		txtPicture.setBackground(Color.BLUE);
		txtPicture.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPicture.setText("Image Proccessing Application - David Walsh");
		txtPicture.setBounds(10, 11, 424, 35);
		frame.getContentPane().add(txtPicture);
		txtPicture.setColumns(10);
		
		//Creates the show original button.
		JButton original = new JButton("Show Original");
		original.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.getPicture().show();
			}
		});
		original.setBounds(10, 74, 114, 23);
		frame.getContentPane().add(original);
		
		txtClickBelowTo = new JTextField();
		txtClickBelowTo.setText("Click Below to change the image...");
		txtClickBelowTo.setBounds(159, 75, 237, 35);
		frame.getContentPane().add(txtClickBelowTo);
		txtClickBelowTo.setColumns(10);
		
		//Creates the letters button
		JButton letters = new JButton("Letters");
		letters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = new ConnectedComponentImage(
						"C:/Users/David/workspace/ConnectorStarter/images/simple.jpg");

			}
		});
		letters.setForeground(Color.RED);
		letters.setBounds(225, 121, 114, 23);
		frame.getContentPane().add(letters);
		
		//Creates the bacteria button
		JButton bacteria = new JButton("Bacteria");
		bacteria.setForeground(Color.RED);
		bacteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				image = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/bacteria.bmp");
			}
		});		
		bacteria.setBounds(225, 155, 114, 23);
		frame.getContentPane().add(bacteria);
		
		//Creates the plates button
		JButton plates = new JButton("License plate");
		plates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/carplate.jpg");
			}
		});
		plates.setForeground(Color.RED);
		plates.setBounds(225, 189, 114, 23);
		frame.getContentPane().add(plates);
		
		
		//Creates the shapes button
		JButton shapes = new JButton("Shapes");
		shapes.setForeground(Color.RED);
		shapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = new ConnectedComponentImage("C:/Users/David/workspace/ConnectorStarter/images/shapes.bmp");
			}
		});
		shapes.setBounds(225, 223, 117, 23);
		frame.getContentPane().add(shapes);
		
		
	}
}
