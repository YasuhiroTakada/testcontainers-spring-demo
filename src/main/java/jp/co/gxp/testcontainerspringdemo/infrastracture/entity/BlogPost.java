package jp.co.gxp.testcontainerspringdemo.infrastracture.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.ToString;

@Entity(name = "blog_post")
@ToString
public class BlogPost implements Serializable {

  @Id
  @GeneratedValue
  public UUID id;

  @Column
  public String title;

  @Column(columnDefinition = "json")
  public String content;

  @Column
  public String category;

  @Column
  public Long likeCount;

  @ManyToOne
  public Author author;

  public static BlogPost of(Author author, String title, String content) {
    BlogPost instance = new BlogPost();
    instance.author = author;
    instance.title = title;
    instance.content = content;
    return instance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlogPost blogPost = (BlogPost) o;
    return id.equals(blogPost.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
