package jp.co.gxp.testcontainerspringdemo.blog

import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.Author
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost
import jp.co.gxp.testcontainerspringdemo.infrastracture.repository.AuthorRepository
import jp.co.gxp.testcontainerspringdemo.infrastracture.repository.BlogPostRepository
import spock.lang.Specification

class MockBlogPostServiceSpec extends Specification {

    BlogPostService sut

    def 'ブログを投稿し、その記事が取得できる'() {
        given:
        // Authorのモック
        def donald = new Author(id: UUID.randomUUID(), name: "Donald")
        def authorRepository = Mock(AuthorRepository) {
            1 * findByName("Donald") >> Optional.of(donald)
        }
        // BlogPostのモック
        def donaldBlog = new BlogPost(id: UUID.randomUUID(), author: donald, title: "Java Programing", content: "{\"content\":\"Blog content...\"}", likeCount: 100)
        def blogPostRepository = Mock(BlogPostRepository) {
            1 * findByAuthorAndTitle(donald, "Java Programing") >> Optional.of(donaldBlog)
            1 * findByAuthor_Name("Donald") >> [donaldBlog]
        }
        sut = new BlogPostService(authorRepository, blogPostRepository)

        when:
        sut.create("Donald", "Java Programing", "blog content..")
        List<BlogPost> actual = sut.get("Donald")

        then:
        actual.size() == 1
        actual[0].title == "Java Programing"
    }

}
