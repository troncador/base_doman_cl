package cl.doman.resource.image.thumbs;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Test {
	private int imageheight = 118;
	private int imageWidth = 520;
	
	private int thumbsheight = 118/4;
	private int thumbsWidth = 520/4 ;

	
	
	public Test(FileInputStream fileInputStream){
 		try {
			BufferedImage origin = ImageIO.read(fileInputStream);
			int height = origin.getHeight();
			int width = origin.getWidth();
			
			if(height != imageheight || width != imageWidth){
				System.out.println("no es del tamaño");
			}
			
			BufferedImage thumbs = new BufferedImage(thumbsWidth,thumbsheight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics2D = thumbs.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
			graphics2D.drawImage(origin, 0, 0, thumbsWidth,thumbsheight,0, 0, imageWidth,imageheight, null);
			ByteArrayOutputStream a = new ByteArrayOutputStream ();
			ImageIO.write(thumbs,"png",a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public OutputStream aest(FileInputStream fileInputStream) throws IOException, IllegalImage{
		
		BufferedImage origin = ImageIO.read(fileInputStream);
		int height = origin.getHeight();
		int width = origin.getWidth();
		
		
		if(height != imageheight || width != imageWidth){
			throw new IllegalImage("Illegal Size");
		}
		
		BufferedImage thumbs = new BufferedImage(thumbsWidth,thumbsheight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = thumbs.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
		graphics2D.drawImage(origin, 0, 0, thumbsWidth,thumbsheight,0, 0, imageWidth,imageheight, null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream ();
		ImageIO.write(thumbs,"png",outputStream);
		return outputStream;
	}
	private static final transient Logger log =LoggerFactory.getLogger(Test.class);
	static public void main(String[] arg) throws IOException{
		File inputFile = new File("/home/troncador/Desktop/actividadejemplo10.png");
		FileInputStream fileInputStream = new FileInputStream(inputFile);
		
		new Test(fileInputStream);
	}
}
