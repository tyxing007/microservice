package com.dengo.erp.service;

import com.dengo.erp.config.StaticResourceConfiguration;
import com.dengo.erp.config.properties.ImageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoService
 * This service is designed to create preview images and saving them on the server
 *
 * @author Andrii Blyznuk, Sergey Petrovsky
 */
@Service
@EnableConfigurationProperties(ImageProperties.class)
public class PhotoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    ImageProperties imageProperties;

    //This method takes a picture,
    // the path to it, and the amount you want to trim it.
    // This method makes preview photo and saves on server
    // original and cropped photo
    public List<String> uploadPhotoAndMakePreview(MultipartFile file, String pathResources, Integer previewSize) throws IOException, NoSuchAlgorithmException {
        List<String> list = new ArrayList<>();
        String originalName = file.getOriginalFilename().toLowerCase();
        String format = null;
        if (originalName.contains(".")) {
            String[] arr = originalName.split("\\.");
            format = arr[arr.length - 1];
        }

        String pathToOriginalPhoto = saveOriginalFile(file, pathResources, format);
        String pathToPreviewPhoto = makePreview(file, pathResources, format, previewSize);

        list.add(0,pathToOriginalPhoto);
        list.add(1,pathToPreviewPhoto);
        return list;
    }

    //This method checks the photo on specified conditions
    // such as the value of photography and its format
    public List<String> checkingPhoto(MultipartFile photo) throws IOException, NoSuchAlgorithmException {
        List<String> listPhoto = new ArrayList<>();
        if (photo.getSize() > Long.parseLong(imageProperties.getMaxUploadFile())) {
            LOGGER.error("Uploading file with size " + photo.getSize() + ", required less then " + imageProperties.getMaxUploadFile());
        } else if (!photo.getContentType().contains("image/")) {
            LOGGER.error("Uploading file with MIME type " + photo.getContentType() + ", required image/*");
        } else {
            listPhoto = uploadPhotoAndMakePreview(photo, StaticResourceConfiguration.CUSTOM_STATIC_PATH, imageProperties.getPreviewSize());
        }
        return listPhoto;
    }

    //This method keeps the server is not truncated (original) photo
    private String saveOriginalFile(MultipartFile multipartFile,  String pathResources, String format) throws IOException, NoSuchAlgorithmException {
        LOGGER.debug("Save original version of uploading photo");
        auditEnablePathFile(pathResources + imageProperties.getOriginalImagePath());
        BufferedImage bi =  ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes()));
        String relativePathToImg = imageProperties.getOriginalImagePath() + getHash(multipartFile.getInputStream(), "MD5", 1024) + "." + format;
        File path = new File(pathResources + relativePathToImg);
        ImageIO.write(bi, format, path);
        return relativePathToImg;
    }

    //The method that checks the filename. If such a path exists it is created
    private void auditEnablePathFile(String pathResources){
        File uploadRootDir = new File(pathResources);
        if (!uploadRootDir.exists()){
            uploadRootDir.mkdirs();
        }
    }

    //The method that cuts the photo and make preview
    private String makePreview(MultipartFile multipartFile,  String pathResources, String format, Integer previewSize) throws IOException, NoSuchAlgorithmException {

        auditEnablePathFile(pathResources + imageProperties.getPreviewImagePath());
        InputStream inputStream = multipartFile.getInputStream();
        String relativePathToPreview = imageProperties.getPreviewImagePath() + getHash(inputStream, "MD5", 1024) + "." + format;
        File path = new File(pathResources + relativePathToPreview);
        BufferedImage sourceImage = ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes()));

        Integer width = sourceImage.getWidth();
        Integer height = sourceImage.getHeight();
        if (width > height) {
            sourceImage = cropImageWithBiggerWidth(sourceImage, width, height, previewSize);
        } else if (width < height) {
            sourceImage = cropImageWithBiggerHeight(sourceImage, width, height, previewSize);
        } else {
            sourceImage = cropImageEquallySide(sourceImage, previewSize);
        }
        ImageIO.write(sourceImage, format, path);
        return relativePathToPreview;
    }

    //The method is used when the width then photos larger than the height. This method cuts the photo
    private BufferedImage cropImageWithBiggerWidth(BufferedImage sourceImage, Integer width, Integer height, Integer previewSize){
        float extraSize = height - previewSize;
        float percentHeight = (extraSize / height) * previewSize;
        float percentWidth = width - ((width / previewSize) * percentHeight);
        BufferedImage img = new BufferedImage((int) percentWidth, previewSize, BufferedImage.TYPE_INT_RGB);
        Image scaledImage = sourceImage.getScaledInstance((int) percentWidth, previewSize, Image.SCALE_SMOOTH);
        img.createGraphics().drawImage(scaledImage, 0, 0, null);
        img = img.getSubimage((int) ((percentWidth - previewSize) / 2), 0, previewSize, previewSize);
        return img;
    }

    //The method then used when the height of pictures equal width. This method cuts the photo
    private BufferedImage cropImageEquallySide(BufferedImage sourceImage, Integer previewSize){
        BufferedImage img = new BufferedImage(previewSize, previewSize, BufferedImage.TYPE_INT_RGB);
        Image scaledImage = sourceImage.getScaledInstance(previewSize, previewSize, Image.SCALE_SMOOTH);
        img.createGraphics().drawImage(scaledImage, 0, 0, previewSize, previewSize, null);
        img = img.getSubimage(0, 0, previewSize, previewSize);
        return img;
    }

    //The method is used when the height then photos larger than the width. This method cuts the photo
    private BufferedImage cropImageWithBiggerHeight(BufferedImage sourceImage, Integer width, Integer height, Integer previewSize){
        float extraSize = width - previewSize;
        float percentWidth = (extraSize / width) * previewSize;
        float percentHight = height - ((height / previewSize) * percentWidth);
        BufferedImage img = new BufferedImage(previewSize, (int) percentHight, BufferedImage.TYPE_INT_RGB);
        Image scaledImage = sourceImage.getScaledInstance(previewSize, (int) percentHight, Image.SCALE_SMOOTH);
        img.createGraphics().drawImage(scaledImage, 0, 0, null);
        img = img.getSubimage(0, (int) ((percentHight - previewSize) / 2), previewSize, previewSize);
        return img;
    }

    public static String getHash(InputStream inputStream, String algorithm, Integer buffer) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        Integer bytesRead = -1;
        byte[] bytesBuffer = new byte[buffer];
        while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
            digest.update(bytesBuffer, 0, bytesRead);
        }

        byte[] hashedBytes = digest.digest();

        //Convert byte array to Hex string
        StringBuilder stringBuffer = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            stringBuffer.append(Integer.toString((hashedByte & 0xff) + 0x100, 16)
                    .substring(1));
        }


        return stringBuffer.toString();
    }


}
