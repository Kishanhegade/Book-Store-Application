package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
