package com.forumsite.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

/**
 * 
 * Service Layer interface providing
 * basic save and retrieve operations
 * for images.
 * @throws IOExceptions when missing files
 * or lacking credentials to save in directories 
 */
public interface ImageService  {
	/**
	 * Retrieve an image as a base 64 string
	 * @param path of the file to use
	 * @return an image converted to a base 64 string
	 * @throws IOException
	 */
	String getB64(String path);
	
	File loadImage(String path);
	
	/**
	 * Saves an image to a predefined destination
	 * @param file
	 * @throws IOException
	 */
	String saveImage(Part image) throws IOException;

	/**
	 * Remove and image from the default image folder
	 * @param fileName
	 * @throws IOException
	 */
	void removeImage(String fileName) throws IOException;

}
