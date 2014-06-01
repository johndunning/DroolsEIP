package com.skills421;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.impl.DefaultCamelContext;

import com.skills421.model.Request;

public class RequestClientUsingCamel
{
	static String serviceUrl = "http://localhost:8080/ServletExample/requestService";
	
	private static void printExchange(String name,Exchange exchange)
	{
		System.out.format("%s - Start\n",name);
		System.out.println(exchange.getIn().getBody());
		if(exchange.hasOut())
		{
			System.out.println(exchange.getOut().getBody());
		}
		System.out.format("%s - End\n",name);
		System.out.println();
	}

	public static void main(String args[]) throws Exception
	{
		CamelContext context = new DefaultCamelContext();
		
		final CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress(serviceUrl);
        cxfEndpoint.setWsdlURL("http://localhost:8080/ServletExample/requestService?wsdl");
        cxfEndpoint.setCamelContext(context);
        cxfEndpoint.setDataFormat(DataFormat.POJO);
        cxfEndpoint.setServiceClass("com.skills421.services.RequestService");
        
		context.addRoutes(new RouteBuilder()
		{
			public void configure()
			{
				from("direct:request")
				.setExchangePattern(ExchangePattern.InOut)
				.process(new Processor()
				{				
					@Override
					public void process(Exchange exchange) throws Exception
					{
						printExchange("Process", exchange);						
					}
				})
				.to(cxfEndpoint);
			}
		});
		
		context.start();
		
		Request request = new Request("Request header from Camel", "Request data from Camel");
		Exchange requestExchange = ExchangeBuilder.anExchange(context).withBody(request).build();
		
		ProducerTemplate template = context.createProducerTemplate();		
		Exchange responseExchange = template.send("direct:request", requestExchange);
		
		printExchange("ResponseExchange", responseExchange);
		
		context.stop();
	}
}
