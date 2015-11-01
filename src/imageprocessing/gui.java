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

public class gui {

	private JFrame frame;
	private JTextField txtPicture;
	private ConnectedComponentImage image;
	private JLabel picBoundry;
	private BufferedImage img;

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
		
		

		JButton btnReturnCount = new JButton("Return Count");
		btnReturnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.WeightedQU();
				JOptionPane.showMessageDialog(null, "There are " +(image.countComponents()-1) +" objects in the image, and 1 background component ");
			}
		});
		btnReturnCount.setBounds(10, 70, 114, 23);
		frame.getContentPane().add(btnReturnCount);
		
		JButton btnNewButton = new JButton("Show Binary");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				image.binaryComponentImage().show();; 
				
				
			}
		});
		btnNewButton.setBounds(10, 104, 114, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnColourImage = new JButton("Colour Image");
		btnColourImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.colourComponentImage().show();
			}
		});
		btnColourImage.setBounds(10, 138, 114, 23);
		frame.getContentPane().add(btnColourImage);
		
		JButton btnNewButton_1 = new JButton("Boundry Box");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.identifiedComponentImage().show();
			}
		});
		btnNewButton_1.setBounds(10, 172, 114, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		txtPicture = new JTextField();
		txtPicture.setForeground(Color.WHITE);
		txtPicture.setBackground(Color.BLUE);
		txtPicture.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPicture.setText("Image Proccessing Application - David Walsh");
		txtPicture.setBounds(10, 11, 424, 35);
		frame.getContentPane().add(txtPicture);
		txtPicture.setColumns(10);
		
		JLabel pic = new JLabel("");
		pic.setLabelFor(frame);
		pic.setIcon(new ImageIcon("C:/Users/David/workspace/ConnectorStarter/images/bacteria.bmp"));
		pic.setBounds(171, 74, 232, 133);
		frame.getContentPane().add(pic);
		
		
	}
}
