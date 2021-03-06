package org.springframework.boot.autoconfigure.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.borframework.service.BotService;
import org.springframework.social.borframework.service.impl.BotServiceImpl;
import org.springframework.social.botframework.api.BotFramework;
import org.springframework.social.botframework.api.proxy.BotFrameworkReConnectHandler;
import org.springframework.social.botframework.connect.BotFrameworkConnectionFactory;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import java.lang.reflect.Proxy;

/**
 * @author Anton Leliuk
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, BotFrameworkConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.microsoft.skype", name = "app-id")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class BotFrameworkAutoConfiguration {

    @Configuration
    @EnableSocial
    @EnableConfigurationProperties(BotFrameworkProperties.class)
    @ConditionalOnWebApplication
    protected static class BotFrameworkConfigurerAdapter extends SocialConfigurerAdapter {

        @Autowired
        private BotFrameworkProperties properties;

        @Override
        public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
            connectionFactoryConfigurer.addConnectionFactory(botFrameworkConnectionFactory());
        }

        @Override
        public UserIdSource getUserIdSource() {
            return () -> properties.getAppId();
        }

        @Bean
        @ConditionalOnMissingBean(BotFramework.class)
        public BotFramework botFramework(ConnectionRepository repository, BotFrameworkConnectionFactory connectionFactory){
            Class<?>[] interfaces = {BotFramework.class};
            return (BotFramework) Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, new BotFrameworkReConnectHandler(repository, connectionFactory, properties.getScope()));
        }

        @Bean
        public ConnectionRepository connectionRepository(UsersConnectionRepository usersConnectionRepository) {
            return usersConnectionRepository.createConnectionRepository(getUserIdSource().getUserId());
        }

        @Bean
        public BotFrameworkConnectionFactory botFrameworkConnectionFactory(){
            return new BotFrameworkConnectionFactory(properties.getAppId(), properties.getAppSecret(), properties.getAuthUrl());
        }

        @Bean
        public BotService botService(){
            return new BotServiceImpl();
        }
    }
}
