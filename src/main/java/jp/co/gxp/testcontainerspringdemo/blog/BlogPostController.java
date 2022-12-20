package jp.co.gxp.testcontainerspringdemo.blog;

import java.util.List;

import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.BlogPost;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @RequestMapping("/authors/{author}/blogs")
    public List<BlogPost> getBlogs(@PathVariable String author) {
        return blogPostService.get(author);
    }
}
