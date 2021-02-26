package mapper;

import com.zstao.ReadInterestingApplication;

import com.zstao.entity.Comment;
import com.zstao.mapper.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ReadInterestingApplication.class)
public class CommentTeat {
    @Resource
    private CommentDao cd;

    @Test
    public void insert(){
        Comment comment = new Comment();
        comment.setArticleId("1");
        comment.setCMain("已转发");
        comment.setStatus(1);
        comment.setUserId("1");
        System.out.println(cd.saveComment(comment));

    }

    @Test
    public void listTest(){
        System.out.println(cd.listComments());
        System.out.println(cd.getComment(1));
    }

}
