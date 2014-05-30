package com.skills421.beans;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;

import com.skills421.model.Request;
import com.skills421.model.Response;

@Service
public class RuleService
{

	public RuleService()
	{
		// TODO Auto-generated constructor stub
	}

	public Response runRules(Request request)
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
        
        Response response = new Response();
        
        ksession.setGlobal("RESPONSE", response);
 
        ksession.fireAllRules();
        
        return response;
    }
}
