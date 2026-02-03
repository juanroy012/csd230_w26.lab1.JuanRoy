package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}

