package com.codegym.repository;

import com.codegym.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<Size, Integer> {
}
