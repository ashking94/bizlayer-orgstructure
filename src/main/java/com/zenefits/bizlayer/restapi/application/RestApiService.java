package com.zenefits.bizlayer.restapi.application;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenefits.bizlayer.restapi.resources.ThirdPartyResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class RestApiService extends Application<RestApiConfig> {

	private static Logger LOGGER = LoggerFactory.getLogger(RestApiService.class);

	public static void main(String[] args) throws Exception {
		new RestApiService().run(args);
	}

	@Override
	public void initialize(Bootstrap<RestApiConfig> bootstrap) {

		// Enable variable substitution with environment variables
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

		bootstrap.addBundle(new SwaggerBundle<RestApiConfig>() {

			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(RestApiConfig config) {
				return config.swaggerBundleConfiguration;
			}

		});

	}

	@Override
	public void run(RestApiConfig configuration, Environment environment) throws Exception {

		LOGGER.info("Registering resources");
		environment.jersey().register(new ThirdPartyResource());

		// Enable CORS headers
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

	}

}
