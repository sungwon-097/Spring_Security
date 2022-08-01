package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

// CRUD 함수를 들고 있음
// @Repository 가 없어도 IoC 된다(JpaRepository 상속했기 때문)
public interface UserRepository extends JpaRepository<User, Integer> { // User 의 primaryKey 는 Integer
}
