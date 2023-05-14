package io.dev.deneb.repo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import io.dev.deneb.model.QUser;
import io.dev.deneb.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static io.dev.deneb.model.QPost.post;
import static io.dev.deneb.model.QUser.user;

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
        return query
            .select(user)
            .from(user)
            .leftJoin(user.posts, post).on(user.id.eq(post.user.id))
            .where(user.id.eq(id))
            .fetchOne();
    }

    @Override
    public User findByConditions(String email, boolean disabled) {
        JPAQuery<User> query = new JPAQuery<>(em);
        QUser user = QUser.user;
        return query
            .select(user)
            .from(user)
            .where(
                conditions(email, disabled))
            .fetchOne();
    }

    BooleanBuilder conditions(String email, boolean disabled) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(StringUtils.hasText(email) ? user.email.eq(email) : null);
        builder.and(user.disabled.eq(disabled));
        return builder;
    }

}
