package mapper;

import com.zstao.ReadInterestingApplication;

import com.zstao.entity.ArticleCollect;
import com.zstao.mapper.ArticleCollectDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ReadInterestingApplication.class)
public class ArticleCollectTest {

    @Resource
    private ArticleCollectDao collectDao;
    @Test
    public void saveTest(){
        ArticleCollect collect = new ArticleCollect();
        collect.setArticleId("1");
        collect.setStatus(1);
        collect.setUId("1");
        System.out.println(collect);
        System.out.println(collectDao.saveArticleCollect(collect));

    }
    @Test
    public void listTest(){
        System.out.println(collectDao.listArticleCollects(1));
    }

    @Test
    public void updateTest(){
        System.out.println(collectDao.updateArticleCollect(1,1));
    }

}
