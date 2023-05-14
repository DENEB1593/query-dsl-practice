package io.dev.deneb.repo;

import io.dev.deneb.model.Post;

import java.util.List;

public interface PostRepository {
  Post save(Post post);

  List<Post> findByEmail(String email);
}
