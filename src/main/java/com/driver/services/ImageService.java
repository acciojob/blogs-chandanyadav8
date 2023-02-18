package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        List<Image> listOfImage =blog.getImageList();
        listOfImage.add(image);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){

        Image image=imageRepository2.findById(id).get();
        Blog blog=image.getBlog();
        List<Image> listOfImage =blog.getImageList();
        listOfImage.remove(image);
        blogRepository2.save(blog);
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count=0;
        Image image=imageRepository2.findById(id).get();
        String dimension=image.getDimensions();
        int l=Character.getNumericValue(dimension.charAt(0));
        int b=Character.getNumericValue(dimension.charAt(2));

        int sL=Character.getNumericValue(screenDimensions.charAt(0));
        int sB=Character.getNumericValue(screenDimensions.charAt(2));

        int area=l*b;
        int sArea=sL*sB;

        count=sArea/area;
        return count;
}}
