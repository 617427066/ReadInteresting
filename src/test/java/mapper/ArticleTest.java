package mapper;


import com.zstao.ReadInterestingApplication;
import com.zstao.entity.Article;
import com.zstao.mapper.ArticleDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ReadInterestingApplication.class)
public class ArticleTest {
    @Resource
    private ArticleDao articleDao;

    @Test
    public void insertTest(){
        Article article = new Article();
        article.setAMain("今天天气不错");
        article.setAtypeId("1");
        article.setStatus(1);
        article.setTitle("天气");
        article.setUserId("1");
        article.setViewNumber(0);
        System.out.println(articleDao.saveArticle(article));
    }

    @Test
    public void listTest(){
        System.out.println(articleDao.listArticles(1,5,1));
    }
    @Test
    public void getTest(){
        System.out.println(articleDao.getArticle(1));
    }
    @Test
    public void update(){
        System.out.println(articleDao.updateArticle(2,1));
    }
    @Test
    public void viewNumber(){
        System.out.println(articleDao.updateArticleSeeNumber(3,1));
    }
}
