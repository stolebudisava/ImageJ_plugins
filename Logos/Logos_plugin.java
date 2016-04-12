import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import ij.plugin.frame.*;

public class Logos_plugin implements PlugIn {

	public void run(String arg) {
		//get current image
		ImagePlus curr_img = IJ.getImage(); 
		String title = curr_img.getTitle();
		
		//get current image size
		float orig_width = curr_img.getWidth();
		float orig_height = curr_img.getHeight();
		
		//current image scale factor
		float scale_factor = orig_height/orig_width;
		
		//image heights for each of 3 images
		float dlrg =  150*scale_factor;
		float dnor =  80*scale_factor;
		float dsml =  16*scale_factor;
		
		//values for placing image on the middle of new generated image
		float dlrgH = dlrg/2;
		float dnorH = dnor/2;
		float dsmlH = dsml/2;
		
		int ilrg = (int) Math.round(dlrg);
		int inor = (int) Math.round(dnor);
		int isml = (int) Math.round(dsml);
		
		int yL = 75 - (int) Math.round(dlrgH);
		int yN = 40 - (int) Math.round(dnorH);
		int yS = 8 - (int) Math.round(dsmlH);
		
		//creating images
		ImageProcessor ip_big = curr_img.getProcessor(); 
		ip_big.setInterpolate(true);
		
		ImageProcessor ip_lrg = ip_big.resize(146, ilrg);
		ImageProcessor ip_normal = ip_big.resize(76, inor);
		ImageProcessor ip_sml = ip_big.resize(14, isml);
		
		ImagePlus ipL = NewImage.createRGBImage("lrg-" + title, 150, 150, 1, NewImage.FILL_WHITE);
		ImagePlus ipN = NewImage.createRGBImage(title, 80, 80, 1, NewImage.FILL_WHITE);
		ImagePlus ipS = NewImage.createRGBImage("sml-" + title, 16, 16, 1, NewImage.FILL_WHITE);
		
		ImageProcessor procL = ipL.getProcessor();
		ImageProcessor procN = ipN.getProcessor();
		ImageProcessor procS = ipS.getProcessor();
		
		procL.insert(ip_lrg, 2, yL);
		procN.insert(ip_normal, 2, yN);
		procS.insert(ip_sml, 1, yS);
		
		//creating image names
		ImagePlus lrg = new ImagePlus("lrg-" + title, procL);
		ImagePlus normal = new ImagePlus(title, procN);
		ImagePlus small = new ImagePlus("sml-" + title, procS);
		
		//show generated images
		lrg.show();
		normal.show();
		small.show();
	}
}
