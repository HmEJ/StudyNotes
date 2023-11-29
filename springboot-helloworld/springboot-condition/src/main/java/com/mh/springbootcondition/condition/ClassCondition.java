package com.mh.springbootcondition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @Classname ClassCondition
 * @Description
 * @Date 2023/11/27 下午6:28
 * @Created by joneelmo
 */
public class ClassCondition implements Condition {
    /**
     * @description
     * @author JoneElmo
     * @date 2023-11-27 19:15
     * @param context 上下文对象，可以获取环境 ioc容器 classloader对象
     @param metadata 元数据对象， 可以获取注解定义的属性值
      * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.导入jedis坐标后允许创建bean --返回true,否则返回false.
        //2.判断jedis对应的字节码文件是否存在。如果存在说明引入了jedis依赖，就返回true.否则返回false.
        Map<String, Object> map = metadata.getAnnotationAttributes(ConditionOnClass.class.getName());
        String[] value = (String[]) map.get("value");
        try {
            for (String className : value) {
                Class.forName(className);
            }
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
