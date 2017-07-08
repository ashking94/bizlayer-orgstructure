package com.zenefits.bizlayer.restapi.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenefits.bizlayer.restapi.exceptions.ApplicationException;
import com.zenefits.bizlayer.restapi.exceptions.ExceptionType;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.BaseRequest;

public class DeserializerUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeserializerUtil.class);

	public static BaseRequest deserialize(String request) throws ApplicationException {

		try {
			return new ObjectMapper().readValue(request, BaseRequest.class);
		} catch (IOException e) {
			LOGGER.error("Error while de-serializing the 3rd party Api's response", e);
			throw new ApplicationException(ExceptionType.DESERIALIZATIONERROR,
					ExceptionType.DESERIALIZATIONERROR.toString().toLowerCase(),
					ExceptionType.DESERIALIZATIONERROR.getCode());
		}
	}
}
