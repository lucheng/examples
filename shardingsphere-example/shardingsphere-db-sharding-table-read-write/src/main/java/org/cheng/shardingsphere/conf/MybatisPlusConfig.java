package org.cheng.shardingsphere.conf;

import java.util.List;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;

@Configuration
@MapperScan({ "org.cheng.shardingsphere.mapper" })
public class MybatisPlusConfig extends MybatisPlusAutoConfiguration {

	public MybatisPlusConfig(MybatisPlusProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
			ObjectProvider<TypeHandler[]> typeHandlersProvider,
			ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader,
			ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
			ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
			ApplicationContext applicationContext) {
		super(properties, interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader,
				databaseIdProvider, configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider,
				applicationContext);
	}
	
}
