package com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.adapter;

import java.util.HashMap;
import java.util.Map;

import com.zenefits.bizlayer.restapi.exceptions.ApplicationException;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.restclient.RestClient;

public class ConnectorAdapter {

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

	public String getThirdPartyResponse(String url) throws ApplicationException {

		String response = null;
		requestHeader.put("Authorization", authToken);
		RestClient restClient = new RestClient(url, requestHeader);

		response = restClient.getHttpResponse();
		return response;

	}

}
