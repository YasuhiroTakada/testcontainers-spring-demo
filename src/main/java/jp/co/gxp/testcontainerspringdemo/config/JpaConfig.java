package jp.co.gxp.testcontainerspringdemo.config;

import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.Author;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "jp.co.gxp.testcontainerspringdemo.infrastracture.repository")
@EntityScan(basePackageClasses = {Author.class})
public class JpaConfig {
}
