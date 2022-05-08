package com.example.demo.service;


import com.example.demo.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

}