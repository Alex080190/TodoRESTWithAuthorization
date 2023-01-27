package com.example.todorestwithauthorization.services;

import com.example.todorestwithauthorization.models.User;
import com.example.todorestwithauthorization.repositories.UserRepository;
import com.example.todorestwithauthorization.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user.get());
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }
    public void update(User user) {
        userRepository.save(user);
    }
}
