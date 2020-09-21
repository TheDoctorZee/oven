package com.electrolux.oven.dao;

import com.electrolux.oven.entity.Oven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvenRepository extends JpaRepository<Oven, Long> {

}
