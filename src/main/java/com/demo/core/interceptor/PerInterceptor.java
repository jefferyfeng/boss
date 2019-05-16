package com.demo.core.interceptor;

import com.demo.core.util.MyUtil;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.service.PerService;
import com.demo.modules.permission.entity.SysPermission;
import com.demo.modules.permission.entity.SysUser;
import com.demo.modules.permission.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截器
 *
 */
public class PerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private PerService perService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        temp();

        //校验当前用户是否登陆
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/static/page/404.jsp").forward(request, response);
            return false;
        }

        //查看请求的url是否是所属权限的
        /*tring requestUrl = request.getRequestURL().toString();

        if(requestUrl.contains("/per/toPortal")){
            return true;
        }

        Long[] permissions = user.getPermissions();

        List<SysPermission> permissionsList = sysPermissionService.queryByPermissionIds(permissions);
        boolean flag = false;
        for (SysPermission permission : permissionsList) {
            if(requestUrl.contains(permission.getPermissionUrl())){
                flag = true;
            }
        }
        if(!flag){
            request.getRequestDispatcher("/static/page/404.jsp").forward(request, response);
            return false;
        }*/
        return true;
    }

    public void temp(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setPassword("111111");
        UserVo userVo = perService.login(sysUser);
        MyUtil.getSession().setAttribute("user",userVo);
    }
}
