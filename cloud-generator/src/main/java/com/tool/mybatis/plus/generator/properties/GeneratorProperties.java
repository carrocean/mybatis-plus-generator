package com.tool.mybatis.plus.generator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 代码生成器配置信息
 *
 * @Author carrocean
 * @Date: 2025/06/05
 */

@Data
@Component
@ConfigurationProperties(prefix = "code.generator")
public class GeneratorProperties {
    /**
     * 项目模块名称
     */
    private String moduleName;

    /**
     * 代码生成作者名称
     */
    private String authorName;

    /**
     * 数据库连接 URL
     */
    private String url;

    /**
     * 数据库驱动类名
     */
    private String driverName;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 父包路径
     */
    private String parentPackage;

    /**
     * Mapper XML 文件路径
     */
    private String mapperPath;

    /**
     * DTO 类路径
     */
    private String dtoPath;

    /**
     * VO 类路径
     */
    private String voPath;

    /**
     * QueryDTO 类路径
     */
    private String queryDtoPath;

    /**
     * Controller 模板路径
     */
    private String templateControllerPath;

    /**
     * Service 模板路径
     */
    private String templateServicePath;

    /**
     * ServiceImpl 模板路径
     */
    private String templateServiceImplPath;

    /**
     * Mapper 模板路径
     */
    private String templateMapperPath;

    /**
     * Mapper XML 模板路径
     */
    private String templateMapperXmlPath;

    /**
     * DTO 模板路径
     */
    private String templateDtoPath;

    /**
     * VO 模板路径
     */
    private String templateVoPath;

    /**
     * QueryDTO 模板路径
     */
    private String templateQueryDtoPath;

    /**
     * Controller 请求路径前缀
     */
    private String requestPathPrefix;

    /**
     * 表前缀（生成实体类时会自动去除）
     */
    private String tablePrefixEntity;

    /**
     * 表前缀（生成代码时会自动添加）
     */
    private String tablePrefix;

    /**
     * 表后缀（生成代码时会自动添加）
     */
    private String tableSuffix;

    /**
     * DTO 忽略字段（生成 DTO 时会忽略这些字段）
     */
    private String dtoIgnoreField;

    /**
     * 需要生成代码的表名列表
     */
    private List<String> tables;
}