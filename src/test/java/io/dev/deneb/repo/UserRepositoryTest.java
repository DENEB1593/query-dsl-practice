package io.dev.deneb.repo;

import io.dev.deneb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void saveTest() {
        User user = new User("deneb1593@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());
    }


    @Test
    void findByIdTest() {
        User user = new User("deneb1594@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());

        User found = userRepository.findById(user.getId());

        assertNotNull(found);
        assertNotNull(found.getId());
        assertNotNull(found.getEmail());
    }

    @Test
    void findByConditionsTest() {
        User user = new User("deneb1595@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());

        User found = userRepository.findByConditions(user.getEmail(),false);

        assertNotNull(found);
        assertEquals(found.getEmail(), user.getEmail());
        assertEquals(found.getDisabled(), user.getDisabled());
    }


    @Test
    void findByConditionsWithEmailNullTest() {
        User user = new User("deneb1595@email.com", false);

        userRepository.save(user);

        assertNotNull(user.getId());

        // select u1_0.id,u1_0.disabled,u1_0.email from users u1_0 where u1_0.disabled=?
        User found = userRepository.findByConditions(null,false);

        assertNotNull(found);
        assertEquals(found.getEmail(), user.getEmail());
        assertEquals(found.getDisabled(), user.getDisabled());
    }




}