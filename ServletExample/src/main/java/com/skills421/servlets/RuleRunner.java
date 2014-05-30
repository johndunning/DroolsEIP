package com.skills421.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Servlet implementation class RuleRunner
 */
public class RuleRunner extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RuleRunner()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	private void runRules(PrintWriter writer)
    {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(new ClassPathResource("rules/skills421/examples/rules.drl", getClass()),ResourceType.DRL);
 
        if (kbuilder.hasErrors())
        {
            if (kbuilder.getErrors().size() > 0)
            {
                for (KnowledgeBuilderError kerror : kbuilder.getErrors())
                {
                    System.err.println(kerror);
                }
            }
        }
 
        KnowledgeBase kbase = kbuilder.newKnowledgeBase();
 
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        ksession.setGlobal("writer", writer);
 
        ksession.fireAllRules();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter writer = response.getWriter();
		writer.println("doGet invoked OK");
		
		runRules(writer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

}
