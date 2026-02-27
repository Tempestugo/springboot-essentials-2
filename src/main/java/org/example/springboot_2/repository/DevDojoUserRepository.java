package org.example.springboot_2.repository;

import org.example.springboot_2.domain.DevDojoUser;
import org.example.springboot_2.domain.日本动画片;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevDojoUserRepository extends JpaRepository<DevDojoUser, Long> {
    DevDojoUser findByUsername (String username);

}
