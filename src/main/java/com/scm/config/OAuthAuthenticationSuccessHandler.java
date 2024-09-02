package com.scm.config;

import java.io.IOException;
import java.util.UUID;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
            
                logger.info("OAuthenticationSuccessHandler");
                
                //idendify the provider
                var oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
                String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
                
                System.out.println(authorizedClientRegistrationId);
                
                var oauthUser = (DefaultOAuth2User)authentication.getPrincipal();

                oauthUser.getAttributes().forEach((key,value)->{
                    logger.info(key+ " : " +value);
                });

                User user = new User();
                user.setUserId(UUID.randomUUID().toString());
                user.setRoleList(List.of(AppConstants.ROLE_USER));
                user.setEmailVerified(true);
                user.setEnabled(true);
                user.setPassword("abc@1234");

                if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                     //google
                        /* Google attributes */
                    user.setEmail(oauthUser.getAttribute("email").toString());
                    user.setProfilePic(oauthUser.getAttribute("picture").toString());
                    user.setName(oauthUser.getAttribute("name").toString());
                    user.setProviderUserId(oauthUser.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setAbout("this account is created using google");

                }else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                    //github 
                     /* Git hub attributes */
                     String email = oauthUser.getAttribute("email") != null
                                    ? oauthUser.getAttribute("email").toString() 
                                    : oauthUser.getAttribute("login").toString()+"@gmail.com";

                    String picture = oauthUser.getAttribute("avatar_url").toString();
                    String name = oauthUser.getAttribute("login").toString();
                    String providerUserId = oauthUser.getName();

                    user.setEmail(email);
                    user.setProfilePic(picture);
                    user.setName(name);
                    user.setProviderUserId(providerUserId);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("this account is created using github");

                }else{
                    logger.info("OAuthAuthenticationSuccessHandler : UnKnown provider");

                }

                //save user
                 User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
                 if(user2 == null){
                 userRepo.save(user);
                 System.out.println("Saved User By Google"); 

            }
            response.sendRedirect("/user/profile");

        }
    }
