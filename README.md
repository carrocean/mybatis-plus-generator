# MyBatis-Plus 代码生成器组件

## 简介
这是一个基于 Spring Boot 3.2.4 和 MyBatis-Plus 的代码生成器组件。它能够根据数据库表结构自动生成基础代码，包括 DTO、VO、QueryDTO、Controller、Service、ServiceImpl 和 Mapper 等。通过简单的配置文件，用户可以轻松地生成所需的代码，从而提高开发效率。

## 功能特点
- **通用性**：适用于不同的项目和数据库表结构。
- **灵活性**：支持自定义模板和生成路径。
- **易用性**：通过配置文件即可生成代码，无需手动编写大量重复代码。
- **集成性**：基于 Spring Boot 3.2.4，易于集成到现有项目中。

## 使用方法

### 1. 项目依赖
确保你的项目中包含了以下依赖：
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.12</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.12</version>
</dependency>
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.3</version>
</dependency>
```

### 2. 配置文件
在你的 `generator-config.properties` 文件中添加配置信息

### 3. 模板文件
将模板文件（如 `controller.java.vm`、`service.java.vm` 等）放入项目资源目录中，路径可以根据配置动态指定。模板文件的格式应符合 Velocity 模板语法。

## 注意事项
- **模板文件路径**：确保模板文件路径正确，且模板文件内容符合 Velocity 模板语法。
- **数据库连接**：确保数据库连接信息正确，且数据库可访问。
- **生成路径**：确保生成路径存在，且有写权限。