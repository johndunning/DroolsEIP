package com.skills421.services;

import javax.inject.Inject;
import javax.jws.WebService;

import com.skills421.beans.RuleService;
import com.skills421.model.Request;
import com.skills421.model.Response;

@WebService(endpointInterface="com.skills421.services.RequestService",serviceName="requestService")
public class RequestServiceImpl implements RequestService
{
	@Inject
	private RuleService ruleService;
	
	public Response sendRequest(Request request)
	{
		Response response = ruleService.runRules(request);
		return response;
	}
}
