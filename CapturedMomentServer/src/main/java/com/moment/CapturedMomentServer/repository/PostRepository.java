package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.Post;
import com.moment.CapturedMomentServer.domain.Spot;
import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByWriter(String email);
    public List<Post> findBySpotId(Long id);
}
