package jp.co.gxp.testcontainerspringdemo.blog

import jp.co.gxp.testcontainerspringdemo.config.JpaConfig
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@DataJpaTest
class TestDatabaseBlogPostServiceSpec extends Specification {

    @Autowired
    BlogPostService sut

    @Sql(scripts = "classpath:/sql/testdata.sql")
    def 'ブログを投稿し、その記事が取得できる'() {
        when:
        sut.create("Donald", "Java Programing", "{\"content\":\"blog content..\"}")
        List<BlogPost> actual = sut.get("Donald")

        then:
        actual.size() == 1
        actual[0].title == "Java Programing"
    }

    @Configuration
    @Import([JpaConfig, BlogPostService])
    static class LocalTestContext {

    }
}
