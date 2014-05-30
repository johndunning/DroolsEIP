package com.skills421;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.skills421.model.Request;
import com.skills421.model.Response;
import com.skills421.services.RequestService;

public class RequestClient
{
	public static void main(String[] args)
	{
		String serviceUrl = "http://localhost:8080/ServletExample/requestService";
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(RequestService.class);
		factory.setAddress(serviceUrl);
		
		RequestService requestService = (RequestService) factory.create();

		Request request = new Request("Request header", "Request data");

		Response reponse = requestService.sendRequest(request);

		System.out.println("response : " + reponse);
	}
}
