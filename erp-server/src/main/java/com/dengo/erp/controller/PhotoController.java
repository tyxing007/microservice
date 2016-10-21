package com.dengo.erp.controller;

import com.dengo.erp.config.StaticResourceConfiguration;
import com.dengo.erp.service.PhotoService;
import com.dengo.erp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Photo REST Controller
 * Controller that handles all uploading photo requests from front-end
 *
 * @author Sergey Petrovsky, Andrii Blyznuk
 */
@RestController
public class PhotoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    PhotoService photoService;

    @Autowired
    UserService userService;

    //Method that we obtain a picture that comes from the client side, before you add the photo
    // implemented method for removing photos that are not stored on the server are not usable pictures,
    // and then we called the method of service that already stores the
    // photo on the server and the path to it in the database
    @RequestMapping(value = "/api/image/upload/user/{userId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addPhoto(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile photo, @PathVariable("userId") Long userId) throws IOException, NoSuchAlgorithmException {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        //todo: Remove substring()
        userService.deletePhoto(StaticResourceConfiguration.CUSTOM_STATIC_PATH.substring(0, 9), userId);
        List<String> checkedPhotos = photoService.checkingPhoto(photo);
        if(!checkedPhotos.isEmpty()) userService.addPhoto(checkedPhotos, userId);
    }

	//Method that we obtain a user ID in order to
    // get the path on which the photo and delete it
    @RequestMapping(value = "/api/image/delete/user/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePhoto (HttpServletRequest httpServletRequest, @PathVariable("userId") Long userId) throws IOException {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        //todo: Remove substring()
        userService.deletePhoto(StaticResourceConfiguration.CUSTOM_STATIC_PATH.substring(0, 9),userId);
    }


}
