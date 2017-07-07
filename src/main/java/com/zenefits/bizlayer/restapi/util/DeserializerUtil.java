package com.zenefits.bizlayer.restapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.BaseRequest;

public class DeserializerUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeserializerUtil.class);

	public static BaseRequest deserialize(String request) throws Exception {

		try {
			return new ObjectMapper().readValue(request, BaseRequest.class);
		} catch (Exception e) {
			LOGGER.error("Error", e);
			throw new Exception("123");
		}

	}

}
