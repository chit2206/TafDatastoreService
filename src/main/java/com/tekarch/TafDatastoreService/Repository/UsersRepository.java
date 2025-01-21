package com.tekarch.TafDatastoreService.Repository;

import com.tekarch.TafDatastoreService.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAllById(Long id);
}
