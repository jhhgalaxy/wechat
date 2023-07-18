package com.szcgc.wechat.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**   
* @Title: LocalDateTimeAttributeConverter.java 
* @Description: TODO
* @author chenxinli  
* @date May 15, 2019 5:11:31 PM 
* @version V1.0   
*/
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDate locDate) {
		return (locDate == null ? null : Timestamp.from(locDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant()));
	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {
		return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime().toLocalDate());
	}
}
