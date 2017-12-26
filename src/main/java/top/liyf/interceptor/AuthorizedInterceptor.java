package top.liyf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.liyf.domain.User;

import java.util.ArrayList;
import java.util.Arrays;

import static top.liyf.util.common.HrmConstants.USER_SESSION;

/**
 * 判断用户权限的Spring MVC的拦截器
 */
public class AuthorizedInterceptor implements HandlerInterceptor {

    /**
     * 定义不需要拦截的请求
     */
    private static final ArrayList<String> IGNORE_URI = new ArrayList<>(Arrays.asList("/loginForm", "/user/login", "/404.html"));
    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        /** 默认用户没有登录 */
        boolean flag = false;
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        if (user != null) {
            flag = true;
        }
        /** 获得请求的ServletPath */
        String requestURI = request.getRequestURI();

        /**  判断请求是否需要拦截 */
        boolean contains = IGNORE_URI.contains(requestURI.replace("/myhrm", ""));
        /** 如果需要 则拦截请求 */
        if (!contains && !flag) {
            request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
            return false;
        }

        return true;

    }

    /**
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

    }

    /**
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {

    }

}
