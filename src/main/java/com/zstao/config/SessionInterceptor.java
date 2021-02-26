package com.zstao.config;

import com.zstao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//session拦截器
@Component("sessionInterceptor")
public class SessionInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor{


        private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

        /**
         * 检查session中的登录信息，区别ajax
         */
        @Override                                      //获取请求 和发送请求到服务器中   定义类的名称
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            //获取类的名称  赋值给requestURI  参数 进行相关的调用
            String requestURI = request.getRequestURI();
            //在控制台输出  获取到的所有的相关的路径
            // 再次获取参数赋值给uri的问题
            String uri = request.getRequestURI();
            //判断非空
            if (uri != null) {
                //todo 判断是否的登陆的首页  登陆则通过
                //String类型有一个方法：contains（）,该方法是判断字符串中是否有子字符串。如果有则返回true，如果没有则返回false。  page/login是我的登陆页面
                if (uri.contains("/xAdmin/adminLogin")) {// 登录请求直接放行 对于某些不需要验证的uri可以特殊处理

                    return true;

                }
                //todo 判断是否已经登录    此处的("user") 是登陆中获取的，判断是否登陆
                User member = (User) request.getSession().getAttribute("admin");
                if (member == null) {
                    if (isAjax(request)) {
                        LOGGER.info("是ajax请求");
                        //指定格式防止乱码
                        response.setHeader("Cache-Control", "no-cache");
                        response.setHeader("Content-Type", "text/json;charset=utf-8");
                        response.setCharacterEncoding("UTF-8");
                    } else {
                        //重定向页面 如果没有登录就跳转到登录页面   page/login是我的登陆页面
                        response.sendRedirect(request.getContextPath() + "/xAdmin/adminLogin");
                        //停止 运行
                        return false;
                    }

                }
            }
            //继续 运行
            return true;
        }

        /**
         * 处理成功才进入post处理
         */
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            //LOGGER.info(">>>>>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

        }

        /**
         * 处理完后进入，不论是否抛除异常
         */
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            //LOGGER.info(">>>>>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        }

        /**
         * @param request
         * @return
         * @describe 判断是否是ajax请求
         */
        private boolean isAjax(HttpServletRequest request) {
            String xrw = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equalsIgnoreCase(xrw)) {
                return true;
            }
            return false;
        }

}

