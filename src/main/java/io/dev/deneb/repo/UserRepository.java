package io.dev.deneb.repo;

import io.dev.deneb.model.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);

    User findByConditions(String email, boolean disabled);



}
