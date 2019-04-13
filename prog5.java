
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.*;

import javax.imageio.ImageIO;


public class prog5 {

	// Defining Character Array you can change accordingly 
	private static final char[] chars = { '1', 'A', 'a', 'B', 'b', 'C',
			'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
			'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
			'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
			'6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
			'Y', 'y', 'Z', 'z', '9' };
	
	private static final Color[] colors = {Color.red, Color.black, Color.blue};

	// Method for generating the Captcha Code
	public String generateCaptchaText() {

		String randomStrValue = "";

		final int LENGTH = 6; // Character Length

		StringBuffer sb = new StringBuffer();

			int index = 0;
			
			for (int i = 0; i < LENGTH; i++) {
				// Getting Random Number with in range(ie: 60 total character present)
				index = (int) (Math.random() * (chars.length - 1));
				sb.append(chars[index]); // Appending the character using StringBuffer
			}
			
			randomStrValue = String.valueOf(sb); // Assigning the Generated Password to String variable
		
		return randomStrValue;
	}
	
	// Method used to render the Image for Captcha
	public void renderImage(String value) {
		
		if(value != null && !value.isEmpty()) {
			
			BufferedImage image = null;
			
			try {
				
				image = ImageIO.read(new File("background.jpg")); // Background Image
				
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		    Graphics g = image.getGraphics();
		   
		    g.setFont(g.getFont().deriveFont(30f));
		    
		    char[] c = value.toCharArray();
		    
		    int x = 20;
		    int y = 50;
 
		    for(int i=0;i<c.length;i++) {
		    	x = x+ 30;		    
		    	g.setColor(colors[(int)(Math.random() * 3)]);
		    	g.drawString(String.valueOf(c[i]), x, y);
		    }
		   
		    g.dispose();
		    
		    try {
				
		    	ImageIO.write(image, "png", new File("Output.png")); // Output Image
		    	System.out.println("Captcha Generated Successfully!!!");
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) throws MalformedURLException, IOException {

		String generatedStrValue = generateCaptchaText();
		renderImage(generatedStrValue);
	}*/
	
}
