package com.example.healthcare_back.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    
    
    boolean existsByBoardNumber(Integer boardNumber);

    static BoardEntity findByBoardNumber(Integer boardNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByBoardNumber'");
    }

    List<BoardEntity> findAllByBoardNumber(Integer boardNumber);

}
