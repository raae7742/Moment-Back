package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

    public List<PostTag> findByPostId(Long id);
}
