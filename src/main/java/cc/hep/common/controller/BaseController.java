package cc.hep.common.controller;

import cc.hep.common.domain.QueryRequest;
import cc.hep.system.domain.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BaseController {

    //获取subject
    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    //获取当前用户
    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    //获取session
    protected Session getSession() {
        return getSubject().getSession();
    }

    //获取session
    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    //使用takon登录
    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getList());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }

    protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
        PageHelper.clearPage();
        return getDataTable(pageInfo);
    }
}
