package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
import com.fast.family.generator.utils.PackageDirUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public class ControllerGenerator {


    /**
     * @param className       类名
     * @param classComment    类描述
     * @param urlStr          类路径
     * @param generatorConfig
     */
    public static void genSingleController(String className,
                                           String classComment,
                                           String urlStr,
                                           GeneratorConfig generatorConfig) {
        genController(className, classComment, urlStr, "ftl", generatorConfig);
    }


    /**
     * @param className       类名
     * @param classComment    类描述
     * @param urlStr          类路径
     * @param generatorConfig
     */
    public static void genOneToOneController(String className,
                                             String classComment,
                                             String urlStr,
                                             GeneratorConfig generatorConfig) {
        genController(className, classComment, urlStr, "ftl/onetoone", generatorConfig);
    }

    /**
     * @param className       类名
     * @param classComment    类描述
     * @param urlStr          类路径
     * @param generatorConfig
     */
    public static void genOneToManyControllerCode(String className,
                                                  String classComment,
                                                  String urlStr,
                                                  GeneratorConfig generatorConfig) {
        genController(className, classComment, urlStr, "ftl/onetomany", generatorConfig);
    }


    /**
     * @param className       类名
     * @param classComment    类描述
     * @param urlStr          类路径
     * @param generatorConfig
     */
    public static void genManyToOneController(String className,
                                              String classComment,
                                              String urlStr,
                                              GeneratorConfig generatorConfig) {
        genController(className, classComment, urlStr, "ftl/manytoone", generatorConfig);
    }

    private static void genController(String className,
                                      String classComment,
                                      String urlStr,
                                      String resourcePath,
                                      GeneratorConfig generatorConfig) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("className", className);
        paramMap.put("classComment", classComment);
        paramMap.put("sysTime", new Date());
        paramMap.put("packageName", generatorConfig.getPackageName());
        paramMap.put("url", urlStr);
        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = ControllerGenerator.class.getClassLoader().getResource(resourcePath);
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "controller//";
            String savePath = filePath + className + "Controller.java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("/controller.ftl");
                template.process(paramMap, fileWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
