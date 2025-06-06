package com.tool.mybatis.plus.generator.init;

import com.tool.mybatis.plus.generator.config.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动代码生成器
 *
 * @Author carrocean
 * @Date: 2025/06/05
 */

@Component
public class GeneratorInit implements CommandLineRunner {

    @Autowired
    private CodeGenerator codeGenerator;

    @Override
    public void run(String... args){
        codeGenerator.generate();
    }
}
