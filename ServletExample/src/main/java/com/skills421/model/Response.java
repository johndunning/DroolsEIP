package com.skills421.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class Response implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String header;
	private Object data;

	public Response()
	{
		
	}
	
	public Response(String header, Serializable data)
	{
		super();
		this.header = header;
		this.data = data;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return String.format("Response [header=%s, data=%s]", header, data);
	}
}
