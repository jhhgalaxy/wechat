package com.szcgc.wechat.Interceptor;

import com.szcgc.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String uri = request.getRequestURI();
    System.out.println("拦截器-访问URI为：" + uri);
    if (StringUtils.contains(uri, "auth")
        || StringUtils.contains(uri, "trans")
        || StringUtils.contains(uri, "cgcWX")
        || StringUtils.contains(uri, "book")
        || StringUtils.contains(uri, "checkToken")
        || StringUtils.contains(uri, "business")
        || StringUtils.contains(uri, "SMS")
        || StringUtils.contains(uri, "Sms")
        || StringUtils.contains(uri, "esign")
        || StringUtils.contains(uri, "mpGetOpenid")
        || StringUtils.contains(uri, "positionList")
        || StringUtils.contains(uri, "uploadimage")
        || StringUtils.contains(uri, "delivery")
        || StringUtils.contains(uri, "resumeinfo")
        || StringUtils.contains(uri, "resources")
        || StringUtils.contains(uri, "assets")
        || StringUtils.contains(uri, "trip")
        || StringUtils.contains(uri, "getProjectApply")
        || StringUtils.contains(uri, "portal")) {
      System.out.println("访问授权页面，通过！");
      return true;
    }

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user != null) {
      System.out.println("已经过授权，通过!");
      return true;
    }

    System.out.println("未经过授权，不通过，跳转至授权登陆页面!");
    uri = StringUtils.right(uri, uri.length() - 1);
    session.setAttribute("returnUri", uri);
    request.getRequestDispatcher("startauth").forward(request, response);

    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }

}
