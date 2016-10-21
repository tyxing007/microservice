package com.dengo.erp.controller;

import com.dengo.erp.model.User;
import com.dengo.erp.service.MailService;
import com.dengo.erp.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * User REST Controller
 * Controller that handles all user requests from front-end
 *
 * @author Sergey Petrovsky, Andrii Blyznuk
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getUsers(HttpServletRequest httpServletRequest) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return userService.getUsers();
    }

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUser(HttpServletRequest httpServletRequest, @PathVariable Long userId) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return userService.getUser(userId);
    }


//    @PreAuthorize("#oauth2.hasScope('service')")
    @RequestMapping(value = "/api/user/email/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    //The method that we obtain that object (user) of the
    // client and calls the function of services
    // (passing it the user) that stores a user database,
    // and returns to the client user are saved
    @RequestMapping(value = "/api/user/create",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(HttpServletRequest httpServletRequest, @RequestBody User user){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return userService.saveUser(user);
    }

	//The method used to send (the registered recently) the user
    // an e-mail to confirm registration of. The method takes a
    // JSON which has information such as email and user type.
    // Then create URL token generated from this url and email is sent to the user.
    @RequestMapping(value = "/api/token/send", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody User sendTokenToEmail (@RequestBody String jsonData) throws MessagingException {
        JSONObject data = new JSONObject(jsonData);
        User user = userService.addUserToDbAndCreateToken(data.getString("email"), data.getString("type"));
        String url = "http://localhost:8080/#/user/" + user.getId() + "/check/" + user.getToken();
        String text = "<h1><a href='" + url + "'>Confirm the invitation</a></h1>";
        mailService.sendMessageToEmail(user.getEmail(),"Registration", text);
        return user;
    }

	//The method is executed after the user confirmed the registration of the mail.
    // The method takes a user ID and a unique Tokin of the user.
    // Id is used to find a user's database and used to compare
    // Tokin Tokin of which are stored in the database.
    // As an only son who Tokin then the user can
    // extend the registration of
    @RequestMapping(value = "/api/user/{userId}/check/{token}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Boolean checkUser(HttpServletRequest httpServletRequest, @PathVariable("userId") Long userId, @PathVariable("token") String token) throws IOException {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return userService.confirmEmail(userId,token);
    }

    @RequestMapping(value = "/api/user/{userId}/bind/{departmentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void bindUserWithDepartment(HttpServletRequest httpServletRequest, @PathVariable("userId") Long userId, @PathVariable("departmentId") Long departmentId) throws IOException {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        userService.bindUserWithDepartment(userId, departmentId);
    }
}
