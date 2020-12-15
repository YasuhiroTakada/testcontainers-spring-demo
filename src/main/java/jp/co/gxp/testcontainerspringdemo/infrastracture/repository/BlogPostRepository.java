package jp.co.gxp.testcontainerspringdemo.infrastracture.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.Author;
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {

  Optional<BlogPost> findByAuthorAndTitle(Author author, String title);

  List<BlogPost> findByAuthor_Name(String authorName);

  @Query(nativeQuery = true, value = "select blog_post_with_rank.id as id, category, content, like_count, title, author.id as author_id, author.name as author_name"
      + " from (select *, rank() over (partition by category order by like_count desc) as rank_per_category"
      + "      from blog_post) as blog_post_with_rank"
      + " inner join author on author_id = author.id"
      + " where rank_per_category <= :rankLower"
      + " order by category, rank_per_category")
  List<BlogPost> searchByRankPerCategory(@Param("rankLower") int rankLow);
}
