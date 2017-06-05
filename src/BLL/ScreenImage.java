package BLL;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/*
 * Image handling class found at https://tips4java.wordpress.com/2008/10/13/screen-image/
 * The class has been cut down so only the parts this program uses are left.
 *
 */

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ScreenImage
{
	/*
	 *  Create a BufferedImage for Swing components.
	 *  All or part of the component can be captured to an image.
	 *
	 *  @param  component Swing component to create image from
	 *  @param  region The region of the component to be captured to an image
	 *  @return	image the image for the given region
	*/
	public static BufferedImage createImage(JComponent component, Rectangle region)
	{
        //  Make sure the component has a size and has been layed out.
        //  (necessary check for components not added to a realized frame)

		if (! component.isDisplayable())
		{
			Dimension d = component.getSize();

			if (d.width == 0 || d.height == 0)
			{
				d = component.getPreferredSize();
				component.setSize( d );
			}

			layoutComponent( component );
		}

		BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();

		//  Paint a background for non-opaque components,
		//  otherwise the background will be black

		if (! component.isOpaque())
		{
			g2d.setColor( component.getBackground() );
			g2d.fillRect(region.x, region.y, region.width, region.height);
		}

		g2d.translate(-region.x, -region.y);
		component.paint( g2d );
		g2d.dispose();
		return image;
	}

	static void layoutComponent(Component component)
	{
		synchronized (component.getTreeLock())
		{
			component.doLayout();

    	    if (component instanceof Container)
        	{
            	for (Component child : ((Container)component).getComponents())
	            {
    	            layoutComponent(child);
        	    }
	        }
    	}
    }
}