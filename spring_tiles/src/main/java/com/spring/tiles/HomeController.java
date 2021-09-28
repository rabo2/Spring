package com.spring.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

   @RequestMapping("/test.do")
   public String test() {
      return "test";
   }
   
   /*
    * Tiles 사용(header, left, footer 포함)
    */
   @RequestMapping("/testPage.do")
   public String testPage() {
      return "test.page";
   }
   
   @RequestMapping("/testNo.do")
   public String testNo() {
	   return "test.no";
   }
   
}