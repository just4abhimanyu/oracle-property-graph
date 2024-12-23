package com.oracle.opg.repositories;

import com.oracle.opg.models.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PGQLGraphRepository extends JpaRepository<Vertex, Long> {
    @Query(value = "MATCH (v1)-[r:FRIEND*3]->(v2) RETURN v2", nativeQuery = true)
    List<Vertex> findFriendOfFriendOfFriend(Long startId);
}
