package com.txs.springboottest.jmx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

import java.util.HashMap;

/**
 * @author: tangxiaoshuang
 * @date: 2019/1/8 14:28
 * @desc:
 */
@Configuration
public class MBeanConfiguration {

    @Bean
    public MBeanExporter mBeanExporter(Person person) {
        MBeanExporter mBeanExporter = new MBeanExporter();
        HashMap<String, Object> map = new HashMap<>();
        map.put("person:name=Person",person);
        mBeanExporter.setBeans(map);
        return mBeanExporter;
    }
}
