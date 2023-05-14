package io.dev.deneb.repo;

import com.querydsl.jpa.impl.JPAQuery;
import io.dev.deneb.model.Post;
import io.dev.deneb.model.QPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.dev.deneb.model.QUser.user;

@Repository
public class PostRepositoryImpl implements PostRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Post save(Post post) {
    em.persist(post);
    return post;
  }

  @Override
  public List<Post> findByEmail(String email) {
    JPAQuery<Post> query = new JPAQuery<>(em);
    QPost post = QPost.post;

    return query
        .from(post)
        .innerJoin(post.user, user)
        .where(user.email.eq(email))
        .fetch();
  }
}
