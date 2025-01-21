package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.Models.Users;
import com.tekarch.TafDatastoreService.Repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class usercontroller {
    @Autowired
    private  UsersRepository userRepository;


    @PostMapping
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users users) {
         userRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Users>> getAllTransfers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{userid}")
    public ResponseEntity<List<Users>> getuserById(Long userid) {
        return new ResponseEntity<>(userRepository.findAllById(userid), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Directly update the fields without applying logic
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhone(user.getPhone());
                    userRepository.save(existingUser);
                    return ResponseEntity.ok(existingUser);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
