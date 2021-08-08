package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository{
    private List<User> users = new ArrayList<>();
    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if(!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (user.getEmail() == email)
                    return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
