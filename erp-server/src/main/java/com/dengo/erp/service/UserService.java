package com.dengo.erp.service;

import com.dengo.erp.model.Department;
import com.dengo.erp.model.User;
import com.dengo.erp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

/**
 * User Service
 * Service that handles all user requests from user controller
 *
 * @author Dmitry Sheremet, Sergey Petrovsky, Andrii Blyznuk
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired UserRepository userRepository;
    @Autowired DepartmentService departmentService;

    public User getUser(Long userID) {
        User user = userRepository.findOne(userID);
        if (Optional.ofNullable(user).isPresent()) {
            Set<User> directors = user
                    .getDepartments()
                    .stream()
                    .filter(department -> Optional.ofNullable(department.getDirector()).isPresent() && !department.getDirector().equals(user))
                    .map(Department::getDirector)
                    .collect(Collectors.toSet());

            Set<User> subjects = user
                    .getManagedDepartments()
                    .stream()
                    .map(Department::getEmployees)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());

            user.setDirectors(directors);
            user.setSubjects(subjects);
        }

        return user;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public List<User> getUsers() {
        LOGGER.debug("Getting all Users");
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        LOGGER.debug("Saving User with name: " + user.getName() + " and last name: " + user.getLastName());
        return userRepository.save(user);
    }

    public User addUserToDbAndCreateToken(String email, String type){
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setEmail(email);
        user.setToken(token);
		user.setTypeByString(type);
		LOGGER.info("Saving User with email " + email);	
        return userRepository.save(user);
    }

    public void deleteAll() {
        LOGGER.debug("Deleting all Users");
        userRepository.deleteAll();
    }

    //The method adds the user to the way his photographs and stores in a database
    public void addPhoto(List<String> listPhoto, Long userId){
        User user = userRepository.findOne(userId);
        user.setPhoto(listPhoto.get(0));
        user.setPreviewPhoto(listPhoto.get(1));
        userRepository.save(user);
    }

	//The method that tests equality between Token
    // who came from that address and recorded in the
    // database if they are equal then the method returns true
    public Boolean confirmEmail(Long userId, String token){
        LOGGER.debug("Confirmation email for User with ID:" + userId);
        User user = userRepository.findOne(userId);
        if (Optional.ofNullable(user).isPresent()) {
            return user.getToken().equals(token);
        } else {
            LOGGER.debug("User with ID:" + user + " doesn't exist");
            return null;
        }
    }

    //The method that removes the photo and preview user from the database and server
    @Transactional
    public void deletePhoto(String path, Long userId) throws IOException {
        User user = userRepository.findOne(userId);
        if (user.getPhoto() != null) {
            deleteFile(path + user.getPhoto());
            user.setPhoto(null);
            deleteFile(path + user.getPreviewPhoto());
            user.setPreviewPhoto(null);
            userRepository.save(user);
            LOGGER.debug("Deleting photo of User with ID:" + userId + ", path:" + path);
        } else {
            LOGGER.debug("User with ID:" + userId + "doesn't have photo");
        }
    }

    private void deleteFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            file.delete();
        }
    }

    public User bindUserWithDepartment(Long userId, Long departmentId) {
        User user = userRepository.getOne(userId);
        Department department = departmentService.getDepartment(departmentId);
        if(Optional.ofNullable(user).isPresent() && Optional.ofNullable(department).isPresent()) {
            LOGGER.debug("Binding User with ID:" + userId + " and Department with ID:" + departmentId);
            user.withDepartment(department);
            return saveUser(user);
        } else {
            LOGGER.debug("User with ID:" + userId + " and Department with ID:" + departmentId + "don't exist");
            return null;
        }
    }
}
