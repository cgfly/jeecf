package org.jeecf.osgi.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.collections4.CollectionUtils;

/**
 * jar 包工具类
 * 
 * @author jianyiming
 *
 */
public class JarUtils {

    private static final String CLASS_SUFFIX = ".class";

    /**
     * 删除jar包
     * 
     * @param urls
     */
    public static void delete(URL url) {
        File file = new File(url.getPath());
        file.deleteOnExit();
    }

    /**
     * 删除jar包
     * 
     * @param urls
     */
    public static void delete(URL[] urls) {
        for (URL url : urls) {
            File file = new File(url.getPath());
            file.deleteOnExit();
        }
    }

    /**
     * 获取路径下所有jar文件下所有类名
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static List<String> getClassNames(URL url) throws IOException {
        List<String> packageNames = new ArrayList<>();
        JarFile jarFile = new JarFile(url.getFile());
        List<String> tempPackageNames = JarUtils.getClassNames(jarFile, true);
        if (CollectionUtils.isNotEmpty(tempPackageNames)) {
            tempPackageNames.forEach(packageName -> {
                packageNames.add(packageName);
            });
        }
        return packageNames;
    }

    /**
     * 获取路径下所有jar文件下所有类名
     * 
     * @param urls
     * @return
     * @throws IOException
     */
    public static List<String> getClassNames(URL[] urls) throws IOException {
        List<String> packageNames = new ArrayList<>();
        for (URL url : urls) {
            JarFile jarFile = new JarFile(url.getFile());
            List<String> tempPackageNames = JarUtils.getClassNames(jarFile, true);
            if (CollectionUtils.isNotEmpty(tempPackageNames)) {
                tempPackageNames.forEach(packageName -> {
                    packageNames.add(packageName);
                });
            }
        }
        return packageNames;
    }

    /**
     * 获取jar文件下所有类名
     * 
     * @param jarFile 包名
     * @param flag    是否需要迭代遍历
     * @return
     */
    public static List<String> getClassNames(JarFile jarFile, boolean flag) {
        return getClassNames(jarFile, null, flag);
    }

    /**
     * 递归获取jar所有class文件的名字
     * 
     * @param jarFile
     * @param packageName 包名
     * @param flag        是否需要迭代遍历
     * @return List
     */
    public static List<String> getClassNames(JarFile jarFile, String packageName, boolean flag) {
        List<String> result = new ArrayList<>();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            // 判断是不是class文件
            if (name.endsWith(CLASS_SUFFIX)) {
                name = name.replace(CLASS_SUFFIX, "").replace("/", ".");
                if (flag) {
                    // 如果要子包的文件,那么就只要开头相同且不是内部类就ok
                    if ((packageName == null || name.startsWith(packageName)) && -1 == name.indexOf("$")) {
                        result.add(name);
                    }
                } else {
                    // 如果不要子包的文件,那么就必须保证最后一个"."之前的字符串和包名一样且不是内部类
                    if ((packageName == null || packageName.equals(name.substring(0, name.lastIndexOf("."))))
                            && -1 == name.indexOf("$")) {
                        result.add(name);
                    }
                }
            }
        }
        return result;
    }

}
