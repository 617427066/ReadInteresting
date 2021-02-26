package mapper;



import com.zstao.ReadInterestingApplication;
import com.zstao.entity.School;
import com.zstao.entity.User;
import com.zstao.mapper.SchoolDao;
import com.zstao.mapper.UserDao;
import com.zstao.mapper.UserRoleDao;
import com.zstao.service.UserRoleService;
import com.zstao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 测试用户
 */

@SpringBootTest(classes = ReadInterestingApplication.class)
public class UserTest {
    @Resource
    private UserDao ud;
    @Resource
    private UserService us;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private SchoolDao schoolDao;
    @Test
     public void listUser(){
        List<User> list=ud.listUsers();
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void getUser(){
        System.out.println(us.getSUser(1));
    }

    @Test
    public void justRole(){
        System.out.println(userRoleService.findUserRole(1, 2));
    }

    @Test
    public void saveUser(){
        User user=new User();
        Date date = new Date();
        user.setAge(date);
        user.setEmail("2312@163.com");
        user.setNickname("站好的");
        user.setUname("李四");
        user.setSex("女");
        user.setPhone("12312312");
        user.setPassword("123123");
        user.setIntroduce(null);
        System.out.println(user);
        System.out.println(ud.saveUser(user));
    }
@Test
public void update(){
//    User user = ud.getUser(17);
    School school = new School();
    school.setCollege("zasdfs");
    school.setNumber("123@123.com");
    int user1 = schoolDao.save(school);
    System.out.println(user1);
}

    @Test
    public void seeNumber(){
        System.out.println(ud.userNumber());
    }

    @Test
    public void userResigter(){
        System.out.println(us.userSVerify("1","1"));
    }
    @Test
    public void userRole(){
        System.out.println(userRoleDao.listUserRoleInfo(1));
    }

    @Test
    public void userByPhone(){
        System.out.println(us.userSRegister("cover","123","123"));

    }
    @Test
    public void userBysch(){
        System.out.println(us.findByTime("文理"));

    }
}
