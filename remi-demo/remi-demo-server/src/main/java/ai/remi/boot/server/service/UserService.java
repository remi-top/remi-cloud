package ai.remi.boot.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.vo.UserVO;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表(User)服务定义层
 */
public interface UserService extends IService<User> {

    UserVO getLoginById(String id);

    UserVO getUserById(String id);

    User login(String username, String password);
}
