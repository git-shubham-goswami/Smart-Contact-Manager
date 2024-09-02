package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.EmailService;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //user id : have to generate
        String userId =  UUID.randomUUID().toString();
        user.setUserId(userId);

        //password encoded
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());
        
         String emailToken = UUID.randomUUID().toString();
         user.setEmailToken(emailToken);   
         User savedUser = userRepo.save(user);
         String emailLink = Helper.getLinkForEmailVerification(emailToken);
         emailService.sendEmail(savedUser.getEmail(), "Verify Account | Smart Contact Manager", emailLink );
          
         return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    
    }

    @Override
    public Optional<User> updateUser(User user) {
        User u = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        //update user u from user
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setAbout(user.getAbout());
        u.setProfilePic(user.getProfilePic());
        u.setEnabled(user.isEnabled());
        u.setEmailVerified(user.isEmailVerified());
        u.setPhoneVerified(user.isPhoneVerified());
        u.setProvider(user.getProvider());
        u.setProviderUserId(user.getProviderUserId());
        //save the user u in database
        User save = userRepo.save(u);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {
        User u = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(u);
    
    }

    @Override
    public boolean isUserExist(String id) {
        User u = userRepo.findById(id).orElse(null);
        return u!=null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User u = userRepo.findByEmail(email).orElse(null);
        return u != null ? true : false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
         User user = userRepo.findByEmail(email).orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

}
