package com.scm.services.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String fileName) {

        //code for updoalding the image on cloud and return the url of it
        try {//for now its just an empty array of image size
            byte[] data = new byte[contactImage.getInputStream().available()];
            
            //add data into the array : data
            contactImage.getInputStream().read(data);

            //now upload the data into cloud
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                                        "public_id", fileName
            ));
            
            return this.getUrlFromPublicId(fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String getUrlFromPublicId(String publicId) {
        
        return cloudinary
            .url()
            .transformation( new Transformation<>()
                                 .width(AppConstants.CONTACT_IMAGE_WIDTH)
                                 .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                                 .crop(AppConstants.CONTACT_IMAGE_CROP))
            .generate(publicId);
  
    }

}
