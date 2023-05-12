package io.dev.deneb.repo;

import com.querydsl.jpa.impl.JPAQuery;
import io.dev.deneb.model.QUser;
import io.dev.deneb.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        JPAQuery<User> query = new JPAQuery<>(em);
        QUser user = QUser.user;

        return query.from(user).where(user.id.eq(id)).fetchFirst();
    }
}
