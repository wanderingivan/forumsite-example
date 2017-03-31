package com.forumsite.service.impl;



import java.io.File;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.forumsite.service.ImageService;
import com.forumsite.util.ConfiguredImageUtil;
import com.forumsite.util.ImageUtil;


/** 
 *	Implementation of ImageService
 *	that adds caching for images 
 *  and delegates image manipulation to ImageUtil
 *  @see ImageService
 *  @see  ImageUtil
 *
 */
@Stateless
public class ImageServiceImpl implements ImageService {
	

    private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);
    
	private String placeholderFilename ="placeholder.jpg";
	
	@Inject
	@ConfiguredImageUtil
	private ImageUtil imageUtil;
	
	public ImageServiceImpl(){
		super();
	}
	
	/**
	 * Uses image util to return an image converted as a b64 string.<br/>
	 * Tries to resolve missing files by returning a placeholder
	 * image
	 */
	@Override
	//@Cacheable(value="b64", key="#path")
	public String getB64(String path){
		try{
			return imageUtil.encodeToB64String(path);
        }catch(IOException missingFile){
            logger.error("Cant open file "+ path);
            try{
                return imageUtil.encodeToB64String(placeholderFilename);
            }catch(IOException ignore){
                logger.error("Cant open file "+ placeholderFilename );
                throw new IllegalArgumentException("Can't open placeholder file ");
            }
        }
	}

	/**
	 * Uses image util to return an image loaded in a byte[].<br/>
	 * Tries to resolve missing files by returning a placeholder
	 * image
	 */
	@Override
	//@Cacheable(value="image", key="#path")
	public File loadImage(String path){
		try{
			return imageUtil.loadImage(path);
		}catch(IOException missingFile){
            logger.error("Cant open file "+ path);
            try{
                return imageUtil.loadImage(placeholderFilename);
            }catch(IOException ignore){
                logger.error("Cant open file "+ placeholderFilename );
                throw new IllegalArgumentException("Can't open placeholder file ");
            }
        }
	}



	@Override
	//@CacheEvict(value="image",key="#path")
	public void removeImage(String path) throws IOException {
		File file = new File(path);
		if(file.exists()){
		    file.delete();
		}
	}

	
	@Override
	public String saveImage(Part image) throws IOException{
		return imageUtil.saveImage(image, image.getContentType(), image.getName());
	}

	
}
