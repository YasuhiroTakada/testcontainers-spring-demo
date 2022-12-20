package jp.co.gxp.testcontainerspringdemo.infrastracture.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.ToString;

@Entity(name = "author")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@ToString
public class Author implements Serializable {

  @Id
  @GeneratedValue
  public UUID id;

  @Column
  public String name;

  @OneToMany
  public List<BlogPost> blogPostList;

  public static Author of(String name) {
    Author insatnce = new Author();
    insatnce.name = name;
    return insatnce;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(id, author.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
