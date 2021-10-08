package com.mycompany.myapp.config;



import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public final class JSR310DateConverters {

    private JSR310DateConverters() {}

    @WritingConverter
    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

        public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

        private ZonedDateTimeToDateConverter() {}

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    @ReadingConverter
    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();

        private DateToZonedDateTimeConverter() {}

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    /**
     * Used to be able to store objects which contain properties of type {@link OffsetDateTime}.
     */
    @WritingConverter
    public static class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

        public static final OffsetDateTimeToDateConverter INSTANCE = new OffsetDateTimeToDateConverter();

        private OffsetDateTimeToDateConverter() {}

        @Override
        public Date convert(OffsetDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    /**
     * Used to be able to read objects which contain properties of type {@link OffsetDateTime}.
     */
    @ReadingConverter
    public static class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

        public static final DateToOffsetDateTimeConverter INSTANCE = new DateToOffsetDateTimeConverter();

        private DateToOffsetDateTimeConverter() {}

        @Override
        public OffsetDateTime convert(Date source) {
            return source == null ? null : OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

}
