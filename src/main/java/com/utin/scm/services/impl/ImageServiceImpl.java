package com.utin.scm.services.impl;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.utin.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    // String filename = UUID.randomUUID().toString();

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
        

        try{
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename
            ));

            return this.getUrlFromPublicId(filename);

        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public String getUrlFromPublicId(String publicid) {
        

        return cloudinary
                .url()
                .transformation(
                    new Transformation<>()
                        .width(500)
                        .height(500)
                        .crop("fill")
                )
                .generate(publicid);
    }

}
