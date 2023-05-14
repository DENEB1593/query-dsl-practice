package io.dev.deneb.repo;

import io.dev.deneb.model.Post;
import io.dev.deneb.model.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PostRepository postRepository;

  @Test
  @Order(1)
  @Transactional
  void savePostTest() {
    User user = new User("deneb1593@email.com", false);

    userRepository.save(user);

    assertNotNull(user.getId());

    Post post1 = new Post("title1", "body1", user);
    Post post2 = new Post("title2", "body2", user);
    postRepository.save(post1);
    postRepository.save(post2);

    // select p1_0.id,p1_0.body,p1_0.title,p1_0.user_id from post p1_0 join users u1_0 on u1_0.id=p1_0.user_id where u1_0.email=?
    List<Post> postList = postRepository.findByEmail(user.getEmail());
    assertNotNull(postList);
    assertEquals( 2, postList.size());
  }

}