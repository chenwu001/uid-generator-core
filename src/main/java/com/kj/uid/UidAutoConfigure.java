package com.kj.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kj.uid.impl.CachedUidGenerator;
import com.kj.uid.impl.DefaultUidGenerator;
import com.kj.uid.impl.UidProperties;

/**
 * UID 的自动配置
 * 
 * @author tangyz
 *
 */
@Configuration
@ConditionalOnClass({CachedUidGenerator.class, DefaultUidGenerator.class})
@EnableConfigurationProperties({UidProperties.class,CachedUidGenerator.class})
public class UidAutoConfigure {

	@Autowired
	private UidProperties uidProperties;

	@Bean
	@ConditionalOnMissingBean
	DefaultUidGenerator defaultUidGenerator() {
		return new DefaultUidGenerator(uidProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	CachedUidGenerator cachedUidGenerator() {
		return new CachedUidGenerator(uidProperties);
	}

}
