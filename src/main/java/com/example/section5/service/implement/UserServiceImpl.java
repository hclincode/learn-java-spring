package com.example.section5.service.implement;

import com.example.section5.io.entity.UserEntity;
import com.example.section5.repository.UserRepository;
import com.example.section5.service.UserService;
import com.example.section5.shared.dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity storedUserDetails = userRepo.findByEmail(user.getEmail());
        if (storedUserDetails != null) {
            // TODO
            throw new RuntimeException("Record already exist");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // TODO
        userEntity.setEncryptedPassword(userEntity.getPassword() + "encrypt");
        // TODO
        userEntity.setUserId(userEntity.getId() + "publicId");

        UserEntity storedUserDetials = userRepo.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetials, returnValue);

        return returnValue;
    }
}