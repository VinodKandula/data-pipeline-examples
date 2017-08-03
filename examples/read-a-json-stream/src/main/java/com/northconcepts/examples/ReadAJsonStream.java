/*
 * Copyright (c) 2006-2017 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.DataWriter;
import com.northconcepts.datapipeline.core.StreamWriter;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.json.JsonReader;
import com.northconcepts.datapipeline.transform.BasicFieldTransformer;
import com.northconcepts.datapipeline.transform.TransformingReader;

public class ReadAJsonStream {
	
	public static void main(String[] args) throws Throwable {
    	String url = "http://www.google.com/finance/info?client=ig&q=msft,orcl,adbe";
 
    	BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
    	
    	// remove preceding slashes from stream
    	input.readLine();
    	input.read();
    	input.read();
    	
 
    	DataReader reader = new JsonReader(input)
    	    .addField("symbol", "//array/object/t")
    	    .addField("exchange", "//array/object/e")
    	    .addField("price", "//array/object/l")
    	    .addField("change", "//array/object/c")
    	    .addRecordBreak("//array/object");
        
        reader = new TransformingReader(reader)
        	.add(new BasicFieldTransformer("price").stringToDouble())
        	.add(new BasicFieldTransformer("change").stringToDouble())
        	;
    	
        DataWriter writer = new  StreamWriter(System.out);
   
        JobTemplate.DEFAULT.transfer(reader, writer);
    	
    }

}
