package jp.co.gxp.testcontainerspringdemo.blog;

import java.util.List;
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.Author;
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost;
import jp.co.gxp.testcontainerspringdemo.infrastracture.repository.AuthorRepository;
import jp.co.gxp.testcontainerspringdemo.infrastracture.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogPostService {

  private final AuthorRepository authorRepository;

  private final BlogPostRepository blogPostRepository;

  @Transactional
  public void create(final String authorName, final String title, final String content) {

    // authorは作成済みであること
    Author author = authorRepository.findByName(authorName).get();

    BlogPost blogPost = blogPostRepository.findByAuthorAndTitle(author, title)
        .orElseGet(() -> BlogPost.of(author, title, content));
    blogPost.content = content;
    blogPostRepository.save(blogPost);
  }

  @Transactional(readOnly = true)
  public List<BlogPost> get(String authorName) {
    return blogPostRepository.findByAuthor_Name(authorName);
  }

  @Transactional(readOnly = true)
  public List<BlogPost> getMostLikedTop3PerCategory() {
    return blogPostRepository.searchByRankPerCategory(3);
  }
}
