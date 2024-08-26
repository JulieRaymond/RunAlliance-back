package com.RunAlliance_back.RunAlliance_back.service;

import com.RunAlliance_back.RunAlliance_back.dto.UserRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.mapper.UserMapper;
import com.RunAlliance_back.RunAlliance_back.model.User;
import com.RunAlliance_back.RunAlliance_back.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    // Récupérer tous les utilisateurs
    public List<UserRegistrationDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserRegistrationDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un utilisateur par son ID
    public UserRegistrationDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId));
        return userMapper.userToUserRegistrationDTO(user);
    }

    // Créer un nouvel utilisateur
    public UserRegistrationDTO createUser(UserRegistrationDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }
        User user = userMapper.userRegistrationDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));
        userRepository.save(user);
        return userMapper.userToUserRegistrationDTO(user);
    }

    // Mettre à jour un utilisateur existant
    public UserRegistrationDTO updateUser(Long userId, UserRegistrationDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId));
        userMapper.updateUserFromDTO(userDTO, user);
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
        return userMapper.userToUserRegistrationDTO(user);
    }

    // Supprimer un utilisateur par son ID
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId);
        }
        userRepository.deleteById(userId);
    }

    // Méthode utilisée par le AuthController pour enregistrer un utilisateur
    public User registerUser(String email, String password, Set<String> roles) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return userRepository.save(user);
    }
}