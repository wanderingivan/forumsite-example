package com.forumsite.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumsite.service.ImageService;



@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6286588334830478764L;
    
    @Inject
    ImageService imageService;
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String filename = request.getPathInfo().substring(1);
        File image = imageService.loadImage(filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        
        try(FileInputStream in = new FileInputStream(image)){
            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int count = 0;
            while((count = in.read(buf)) >= 0){
                out.write(buf,0,count);
            }
        }
        
    }

}
