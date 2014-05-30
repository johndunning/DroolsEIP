package com.skills421.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.skills421.model.Request;
import com.skills421.model.Response;

@WebService
public interface RequestService
{
	@WebMethod
	public Response sendRequest(Request request);
}
