package top.liyf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import top.liyf.domain.User;
import top.liyf.service.HrmService;
import top.liyf.util.common.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.liyf.util.tag.PageModel;

import java.util.List;

/**
 * 处理用户请求控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理登录请求
     *
     * @param String loginname  登录名
     * @param String password 密码
     * @return 跳转的视图
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv) {
        // 调用业务逻辑组件判断用户是否可以登录
        User user = hrmService.login(loginname, password);
        if (user != null) {
            // 将用户保存到HttpSession当中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            // 客户端跳转到main页面
            mv.setViewName("redirect:/main");
        } else {
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    @RequestMapping("addUser")
    public String addUser(int flag, User user) {

        if (flag == 1) {
            return "/user/showAddUser";
        }
        if (flag == 2) {
            hrmService.addUser(user);
            return "/user/showAddUser";
        }

        return "xxx";
    }

    /**
     * 处理查询请求
     *
     * @param pageIndex 请求的是第几页
     * @param employee  模糊查询参数
     * @param Model     model
     */
    @RequestMapping("selectUser")
    public String selectUser(User user, PageModel model, Integer flag, HttpServletRequest request) {

        if (flag == null) {

        }
        List<User> users = hrmService.findUser(user, model);
        request.setAttribute("users", users);
        request.setAttribute("pageModel", model);

        return "/user/user";
    }



    /**
     * 处理删除用户请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */


}
