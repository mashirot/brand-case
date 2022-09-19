package ski.mashiro.test;

import org.junit.Test;
import ski.mashiro.pojo.User;
import ski.mashiro.service.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestUserService {
    @Test
    public void testSaveUser() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        UserService userService = userServiceClass.getConstructor().newInstance();
        Method saveUser = userServiceClass.getDeclaredMethod("saveUser", User.class);
        User wangyangming = new User("wangyangming", "666");
        Object rs = saveUser.invoke(userService, wangyangming);
        System.out.println(wangyangming.getId());
        System.out.println(rs);
    }
    @Test
    public void testDeleteUserById() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> constructor = userServiceClass.getConstructor();
        UserService userService = constructor.newInstance();
        Method deleteUserById = userServiceClass.getDeclaredMethod("deleteUserById", int.class);
        System.out.println(deleteUserById.invoke(userService, 13));
    }
    @Test
    public void testGetUser() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        UserService userService = userServiceClass.getConstructor().newInstance();
        Method getUser = userServiceClass.getDeclaredMethod("getUser", User.class);
        Object user = getUser.invoke(userService, new User("lihua", "233"));
        System.out.println(user);
    }
    @Test
    public void testListAllUser() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        UserService userService = userServiceClass.getConstructor().newInstance();
        Method getUser = userServiceClass.getDeclaredMethod("listAllUser");
        Object users = getUser.invoke(userService);
        System.out.println(users);
    }

}
