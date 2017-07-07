package com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.adapter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.restclient.RestClient;

public class ConnectorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorAdapter.class);

	private String authToken;
	private Map<String, String> requestHeader;

	public ConnectorAdapter(String authToken) {
		this.authToken = authToken;
		this.requestHeader = new HashMap<>();
	}

	public ConnectorAdapter() {
		this.authToken = "Bearer UFrtE725SeWv0QZrUHPk";
		this.requestHeader = new HashMap<>();
	}

	public String getThirdPartyResponse(String url) {

		String response = null;
		requestHeader.put("Authorization", authToken);
		RestClient restClient = new RestClient(url, requestHeader);

		try {
			response = restClient.getHttp();
			return response;
		} catch (Exception e) {
			LOGGER.error("Exception while hitting third aprty API", e);
			return "Error";
		}
	}

}
