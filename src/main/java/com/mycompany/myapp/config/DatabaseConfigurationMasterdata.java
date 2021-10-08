package com.mycompany.myapp.config;




import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.test.strapi.masterdata.config.JSR310DateConverters.DateToOffsetDateTimeConverter;
import com.test.strapi.masterdata.config.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.test.strapi.masterdata.config.JSR310DateConverters.OffsetDateTimeToDateConverter;
import com.test.strapi.masterdata.config.JSR310DateConverters.ZonedDateTimeToDateConverter;



@Configuration
@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfigurationMasterdata {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add(ZonedDateTimeToDateConverter.INSTANCE);
        converters.add(OffsetDateTimeToDateConverter.INSTANCE);
        converters.add(DateToOffsetDateTimeConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }
    
    

 

}