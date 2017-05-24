package com.example.messages;


import com.example.users.ViewingsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class MessageListener implements RabbitListenerConfigurer {

    private final ViewingsService ViewingsService;

    public MessageListener(ViewingsService viewingsService) {

        this.ViewingsService = viewingsService;
    }

    //@RabbitListener(queues = "#{'${queue}'}")
    @RabbitListener(queues = "episodic-progress")
    @Transactional
    public void receiveMessage(final ProgessMessage message) {
        ViewingsService.patchViewings(message);
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}