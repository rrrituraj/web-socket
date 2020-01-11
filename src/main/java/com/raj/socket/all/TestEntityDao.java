package com.raj.socket.all;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityDao extends JpaRepository<TestEntity, Integer> {
    TestEntity findByTestId(Integer id);
}
