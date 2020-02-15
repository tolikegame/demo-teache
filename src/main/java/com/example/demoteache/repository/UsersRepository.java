package com.example.demoteache.repository;

import com.example.demoteache.model.po.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
