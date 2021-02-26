package mapper;

import com.zstao.ReadInterestingApplication;

import com.zstao.entity.ArticleType;
import com.zstao.mapper.ArticleTypeDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ReadInterestingApplication.class)
public class articleTypeTest {

    @Resource
    private ArticleTypeDao atd;

    @Test
    public void insertTest(){
        ArticleType type = new ArticleType();
        type.setTypeName("Go语言");
        System.out.println(atd.saveArticleType(type));
    }

    @Test
    public void updateTest(){
        ArticleType articleType = new ArticleType();
        articleType.setTypeName("JAVA");
        articleType.setAtypeId(1);
        System.out.println(atd.updateArticleType(articleType));
    }

    @Test
    public void listTest(){
        System.out.println(atd.listArticleTypes());
        System.out.println(atd.getArticleType(1));
    }
    @Test
    public void deleteTest(){
        System.out.println(atd.deleteArticleType(3));
    }
    @Test
    public void getNumber(){
        System.out.println(atd.articleTypeNumber());
    }

}
