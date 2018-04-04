package com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.restclient;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenefits.bizlayer.restapi.exceptions.ApplicationException;
import com.zenefits.bizlayer.restapi.exceptions.ExceptionType;

public class RestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	private String endPoint;
	private Map<String, String> requestHeader;

	public RestClient(String endPoint, Map<String, String> requestHeader) {
		this.endPoint = endPoint;
		this.requestHeader = requestHeader;
	}

	public String getHttpResponse() throws ApplicationException {

		HttpResponse httpResponse = null;
		String response = "";

		int timeout = 30; // seconds
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout * 3000)
				.setConnectionRequestTimeout(timeout * 3000).setSocketTimeout(timeout * 3000).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpGet getRequest = new HttpGet(this.endPoint);

		if (requestHeader != null && requestHeader.size() > 0)
			for (Map.Entry<String, String> header : requestHeader.entrySet())
				getRequest.setHeader(header.getKey(), header.getValue());

		try {
			httpResponse = httpClient.execute(getRequest);
			response = EntityUtils.toString(httpResponse.getEntity());
			LOGGER.info("Successfully obtained the data from 3rd party Api");
			return response;
		} catch (Exception e) {
			LOGGER.error("Exception while hitting third party API", e);
			throw new ApplicationException(ExceptionType.THIRDPARTYERROR,
					ExceptionType.THIRDPARTYERROR.toString().toLowerCase(), ExceptionType.THIRDPARTYERROR.getCode());
		}

	}

}
