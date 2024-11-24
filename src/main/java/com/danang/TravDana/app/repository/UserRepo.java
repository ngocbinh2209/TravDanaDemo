package com.danang.TravDana.app.repository;

import com.danang.TravDana.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from user where user_name = :userName", nativeQuery = true)
    Optional<UserEntity> findByUserName(String userName);

    @Query(value = "select * from user where email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByRole_RoleId(int roleId);
    
}
