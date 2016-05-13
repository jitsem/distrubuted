/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import Entities.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author jitse
 */
public class ProfanityInterceptor {

   @AroundConstruct
   private void onInit(InvocationContext ic) {
      System.out.println("TEST");
   }

   @AroundInvoke
   private Object doLog(InvocationContext ic) {
      Object obj = null;
      try {
          Message m = (Message) ic.getParameters()[0];
          String replace = m.getText().replace(" ", "%20");
          String url =  "http://www.purgomalum.com/service/containsprofanity?text="+replace;
          URL prof = new URL(url);
          URLConnection pc = prof.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(pc.getInputStream()));
          String inputLine;
          if ((inputLine = in.readLine()) != null){
            if(inputLine.equals("true")){
              m.setText("****** - User, please don't curse");
            }
          }
          in.close();
          
          obj = ic.proceed();
      } catch (Exception ex) {

      } 
      return obj;
   }
}
