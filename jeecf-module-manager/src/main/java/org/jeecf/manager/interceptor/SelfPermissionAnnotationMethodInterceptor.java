package org.jeecf.manager.interceptor;

import java.lang.annotation.Annotation;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.aop.PermissionAnnotationMethodInterceptor;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.subject.Subject;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.PlaceholderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 支持spring 占位符读取配置文件
 * 
 * @author jianyiming
 *
 */
@Component
public class SelfPermissionAnnotationMethodInterceptor extends PermissionAnnotationMethodInterceptor {

    @Autowired
    private Environment env;

    public SelfPermissionAnnotationMethodInterceptor() {
        super(new SpringAnnotationResolver());
    }

    @Override
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
        Annotation annotation = super.getAnnotation(mi);
        RequiresPermissions permAnnotation = (RequiresPermissions) annotation;
        String[] perms = permAnnotation.value();
        for (int i = 0; i < perms.length; i++) {
            String envPerm = PlaceholderUtils.getText(perms[i]);
            if (envPerm != null) {
                envPerm = env.getProperty(PlaceholderUtils.getText(perms[i]));
            }
            if (StringUtils.isNotEmpty(envPerm)) {
                perms[i] = envPerm;
            }
        }
        Subject subject = getSubject();
        if (perms.length == 1) {
            subject.checkPermission(perms[0]);
            return;
        }
        if (Logical.AND.equals(permAnnotation.logical())) {
            getSubject().checkPermissions(perms);
            return;
        }
        if (Logical.OR.equals(permAnnotation.logical())) {
            boolean hasAtLeastOnePermission = false;
            for (String permission : perms) {
                if (getSubject().isPermitted(permission)) {
                    hasAtLeastOnePermission = true;
                }
            }
            if (!hasAtLeastOnePermission) {
                getSubject().checkPermission(perms[0]);
            }
        }
    }
}
