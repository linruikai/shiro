package com.douban.shiro.bind;

import com.douban.shiro.bean.User;
import org.apache.shiro.SecurityUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by ruikai.lin  on 2018/9/23 下午7:29.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

//    @Override
//    public User resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        //CurrentUser currentUser = methodParameter.getParameterAnnotation(CurrentUser.class);
//
//        //nativeWebRequest.getAttribute()
//        return (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
//    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest req, WebDataBinderFactory factory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        Object attribute = req.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
        return (User)attribute;
    }
}
