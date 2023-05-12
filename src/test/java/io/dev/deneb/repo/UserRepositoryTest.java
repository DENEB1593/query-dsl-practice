package io.dev.deneb.repo;

import io.dev.deneb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void saveTest() {
        User user = new User("deneb1593@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());
    }


    @Test
    @Transactional(readOnly = true)
    void findByIdTest() {
        User user = new User("deneb1594@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());

        User found = userRepository.findById(user.getId());

        assertNotNull(found);
        assertNotNull(found.getId());
        assertNotNull(found.getEmail());

    }



}