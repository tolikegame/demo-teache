package com.example.demoteache.repository;

import com.example.demoteache.model.po.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
}
