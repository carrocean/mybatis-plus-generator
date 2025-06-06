package com.tool.mybatis.plus.generator.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import com.tool.mybatis.plus.generator.properties.GeneratorProperties;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器配置
 *
 * @Author carrocean
 * @Date: 2025/06/05
 */

@Component
public class CodeGenerator {
    @Autowired
    private GeneratorProperties generatorProperties;

    public void generate() {
        FastAutoGenerator.create(new DataSourceConfig.Builder(generatorProperties.getUrl(), generatorProperties.getUsername(), generatorProperties.getPassword())) // 数据库配置
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(generatorProperties.getAuthorName()) // 设置作者名
                            .disableOpenDir() // 允许自动打开输出目录
                            .outputDir(System.getProperty("user.dir") + "/" + generatorProperties.getModuleName() + "/src/main/java") // 设置输出目录
                            .dateType(DateType.ONLY_DATE) // 设置时间类型策略
                            .commentDate("yyyy-MM-dd"); // 设置注释日期格式
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(generatorProperties.getParentPackage()) // 设置父包名
                            .entity("entity") // 设置 Entity 包名
                            .mapper("dao") // 设置 Mapper 包名
                            .xml("mapper") // 设置 Mapper XML 包名
                            .service("service") // 设置 Service 包名
                            .serviceImpl("service.impl")// 设置 Service Impl 包名
                            .controller("controller") // 设置 Controller 包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/" + generatorProperties.getModuleName() + generatorProperties.getMapperPath())); // 设置mapper.xml路径配置信息
                })
                // 模板配置、策略配置
                .strategyConfig(builder -> {
                    // 全局策略配置
                    builder.enableCapitalMode() // 开启大写命名（默认 false）
                            .enableSkipView() // 开启跳过视图（默认 false）
                            .disableSqlFilter() // 禁用sql过滤（默认 false），语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
                            .enableSchema() // 启用 schema（默认 false），多 schema 场景时启用
                            .addInclude(generatorProperties.getTables().toArray(new String[0])) // 增加表匹配（内存过滤），支持可变参数和List列表，与 addExclude 互斥，只能配置一项，支持正则匹配，如 ^t_.* 匹配所有以 t_ 开头的表名
                            .likeTable(new LikeTable("")) // 模糊表匹配（SQL 过滤），与 notLikeTable 互斥，只能配置一项
                            .outputFile((path, ot) -> new File(path)); // 自定义模板输出文件处理，暂时不清楚是干啥的

                    // Entity策略配置
                    builder.entityBuilder()
                            .disableSerialVersionUID() // 禁用生成 serialVersionUID（默认 true）
                            .enableLombok() // 开启lombok模型（默认 false），默认添加Getter、Setter、ToString
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解（默认 false）
                            .versionColumnName("version") // 设置乐观锁字段名（数据库字段），versionColumnName 与 versionPropertyName 二选一即可
                            .naming(NamingStrategy.underline_to_camel) // 设置数据库表映射到实体的命名策略（默认值 下划线转驼峰命名）
                            .formatFileName("%sEntity") // 格式化文件名称
                            .enableFileOverride() // 开启覆盖已有文件（默认 false），针对 Entity
                            .addClassAnnotation(new ClassAnnotationAttributes("@Data","lombok.Data")) // 添加类注解，这里主要用于添加其他注解
                            .fieldUseJavaDoc(true); // 设置字段是否生成文档注释（默认 true），当注释字段注释不为空才生效

                    // Controller策略配置
                    builder.controllerBuilder()
                            .formatFileName("%sController") // 格式化文件名称
                            .enableFileOverride() // 开启覆盖已有文件（默认 false），针对 Controller
                            .template(generatorProperties.getTemplateControllerPath()); // 设置自定义模板路径

                    // Service策略配置
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")// 格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")// 格式化 service 接口文件名称
                            .enableFileOverride() // 开启覆盖已有文件（默认 false），针对 Service
                            .serviceTemplate(generatorProperties.getTemplateServicePath()) // 设置自定义service接口模板路径
                            .serviceImplTemplate(generatorProperties.getTemplateServiceImplPath()); // 设置自定义service实现类模板路径

                    // Mapper策略配置
                    builder.mapperBuilder()
                            .enableBaseResultMap() // 开启 BaseResultMap（默认 false）,如果配置了resultMap，mybatis会根据查询到的条目数量自动进行判断，如果是一条就返回对象，如果是多条就返回List对象列表
                            .enableBaseColumnList() // 开启 BaseColumnList
                            .cache(PerpetualCache.class) // 自定义缓存实现类，需实现Cache接口
                            .formatMapperFileName("%sMapper")// 格式化Dao类名称
                            .formatXmlFileName("%sMapper")// 格式化xml文件名称
                            .enableFileOverride() // 开启覆盖已有文件（默认 false），针对 Mapper
                            .mapperTemplate(generatorProperties.getTemplateMapperPath()) // 设置自定义Mapper文件模板路径
                            .mapperXmlTemplate(generatorProperties.getTemplateMapperXmlPath()); // 设置自定义Xml文件模板路径
                })
                // TODO 注入配置
//                .injectionConfig(builder -> {
//                    Map<String, Object> customMap = new HashMap<>();
//                    customMap.put("requestPathPrefix", generatorProperties.getRequestPathPrefix());
//                    customMap.put("dtoPackage", generatorProperties.getParentPackage() + ".support.dto");
//                    customMap.put("voPackage", generatorProperties.getParentPackage() + ".support.vo");
//                    customMap.put("queryDtoPackage", generatorProperties.getParentPackage() + ".support.dto.query");
//                    customMap.put("dtoIgnoreFields", generatorProperties.getDtoIgnoreField());
//                    builder.customMap(customMap) // 自定义配置 Map 对象，用于在模板中访问自定义的配置信息，如项目名称、作者等
//                            .customFile(new CustomFile.Builder() // 自定义配置模板文件，用于指定自定义的模板文件路径，可以格式化文件名
//                                    .fileName("entityDTO.java")
//                                    .templatePath(generatorProperties.getTemplateDtoPath())
//                                    .packageName("dto")
//                                    .build())
//                            .customFile(new CustomFile.Builder()
//                                    .fileName("entityVO.java")
//                                    .templatePath(generatorProperties.getTemplateVoPath())
//                                    .packageName("vo")
//                                    .build())
//                            .customFile(new CustomFile.Builder()
//                                    .fileName("entityQueryDTO.java")
//                                    .templatePath(generatorProperties.getTemplateQueryDtoPath())
//                                    .packageName("querydto")
//                                    .build());
//                })
                // 选择模板引擎
                .templateEngine(new VelocityTemplateEngine()) // 设置使用Velocity引擎模板
                // 执行
                .execute();
    }
}