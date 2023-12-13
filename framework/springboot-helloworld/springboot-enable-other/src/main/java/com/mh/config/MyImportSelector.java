package com.mh.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Classname MyImportSelector
 * @Description
 * @Date 2023/11/29 下午3:57
 * @Created by joneelmo
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.mh.domain.User","com.mh.domain.Role"};
    }
}
