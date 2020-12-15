package jp.co.gxp.testcontainerspringdemo.blog

import jp.co.gxp.testcontainerspringdemo.config.JpaConfig
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers // ・・・（１）
@DataJpaTest(excludeAutoConfiguration = [TestDatabaseAutoConfiguration]) // ・・・（２）
@TestPropertySource(properties = ["spring.jpa.hibernate.ddl-auto=update"]) // ・・・（３）
class TestcontainersBlogPostServiceSpec extends Specification {

    @Autowired
    BlogPostService sut

    // ・・・（４）
    @Shared
    PostgreSQLContainer postgre = new PostgreSQLContainer("postgres:11-alpine")
            .withDatabaseName("main")
            .withUsername("user")
            .withPassword("password")

    void setupSpec() {
        //・・・（５）
        postgre.start()
        System.setProperty('spring.datasource.url', postgre.jdbcUrl)
        System.setProperty('spring.datasource.username', postgre.username)
        System.setProperty('spring.datasource.password', postgre.password)
    }

    @Sql(scripts = "classpath:/sql/testdata.sql")
    def 'ブログを投稿し、その記事が取得できる'() {
        when:
        sut.create("Donald", "Java Programing", "{\"content\":\"\"}")
        List<BlogPost> actual = sut.get("Donald")

        then:
        actual.size() == 1
        actual[0].title == "Java Programing"
    }

    @Configuration
    @Import([JpaConfig, BlogPostService]) //・・・（６）
    static class LocalTestContext {

    }
}
