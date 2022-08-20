package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

// CRUD 함수를 들고 있음
// @Repository 가 없어도 IoC 된다(JpaRepository 상속했기 때문)
public interface UserRepository extends JpaRepository<User, Integer> { // User 의 primaryKey 는 Integer

    // findBy 규칙, Username 문법
    // select * from user where username = ?
    public User findByUsername(String username);

    // select * from user where email = ?
    public User findByEmail(String email);
    public List<User> findByProvider(String provider);
    public List<User> findByRole(String role);

}