package org.forafox.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.forafox.domain.Role;
import org.forafox.domain.User;
import org.forafox.domain.exception.ResourceNotFoundException;
import org.forafox.repository.UserRepository;
import org.forafox.service.UserService;
import org.forafox.web.dto.UserDto;
import org.forafox.web.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        User user = userMapper.toEntity(userDto, null);
        user.setRoles(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByUsername(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PostConstruct
    private void insertUsersIntoDb(){
        createAdminUser();
    }

    private void createAdminUser(){
        User user = new User();
        user.setName("Джон Доу");
        user.setUsername("johndoeadmin@gmail.com");
        user.setPassword(passwordEncoder.encode("JohnDoeAdmin"));
        user.setRoles(Set.of(Role.ADMIN));
        userRepository.save(user);
    }
}
