package com.serverless;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.del.Product;

public class ListProductsHandler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		try {
			// get all products
			List<Product> products = new Product().list();
			// send the response back
			return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(products)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless")).build();
		} catch (Exception ex) {
			logger.error("Error in listing products: " + ex);
			return ApiGatewayResponse.builder().setStatusCode(500).setObjectBody(ex)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless")).build();
		}
	}
}