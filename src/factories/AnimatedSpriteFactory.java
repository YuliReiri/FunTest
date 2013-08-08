package factories;

import interfaces.SceneObject;
import interfaces.SceneObjectFactory;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import physics.AnimatedSprite;
import primitives.AnimatedBitmap;
/**
 * 
 * @author yuli
 *
 * This factory class helps to construct AnimatedSprite object
 * Also it cache and process image data to obtain transparency
 * All parameters that currently hard coded could be obtained from some stream in future
 */
public class AnimatedSpriteFactory extends PrimitiveFactory implements SceneObjectFactory{

	
	private static HashMap<String, Image> _cachedImages = new HashMap<String, Image> ();
	private Image _image;
	
	public AnimatedSpriteFactory(String image_path, Rectangle2D.Double area, Point2D.Double start_speed, Point2D.Double delta_speed) throws IOException {
		super(area, start_speed, delta_speed);
// trying to get image from cache 
		_image = _cachedImages.get(image_path);
		if (_image == null){
// if no image loaded, creating it from file		
// Make magenta as transparent color, could be any color
			_image = makeColorTransparent(ImageIO.read(new File(image_path)), new Color(255,0,255));
			_cachedImages.put(image_path, _image);
		}
	}
	@Override
	public SceneObject createSceneObect() {
		AnimatedSprite sprite = new AnimatedSprite(
				new AnimatedBitmap(_image, 8),getPos(),getVelocity());
		sprite.SetAnimationData(8, 200 + _rnd.nextInt(1000));
		return sprite;
	}
	// make defined color transparent in the image
	protected Image makeColorTransparent(BufferedImage im, final Color color)  
    {  
		ImageFilter filter = new RGBImageFilter()
		{  
			public int markerRGB = color.getRGB();  
  
			public int filterRGB(final int x, final int y, final int rgb)  
			{  
				if ((rgb | 0xFF000000) == markerRGB)  
				{	  
					// Mark the alpha bits as zero - transparent  
					return 0x00FFFFFF & rgb;
				}  
				else  
				{  
					return rgb;
				}  
			}  
		};  
		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);  
		return Toolkit.getDefaultToolkit().createImage(ip);  
    }  
}
