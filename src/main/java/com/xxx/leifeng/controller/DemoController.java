package com.xxx.leifeng.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demo")
public class DemoController {

	/**
	 * URI中包含参数的存取示例
	 * 
	 * @param index
	 * @return
	 */
	@RequestMapping(path = { "page/{index}/path" }, method = { RequestMethod.GET })
	public String page1(@PathVariable int index) {
		return "path="+index;
	}

	/**
	 * 根据参数匹配Action的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/params" }, method = { RequestMethod.GET }, params = { "index" })
	public String page2(String index) {
		return "mathch has params[index]";
	}

	/**
	 * 根据参数匹配Action的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/params" }, method = { RequestMethod.GET }, params = { "!index" })
	public String page3() {
		return "mathch not has params[index]";
	}

	/**
	 * 根据HTTP Header内容匹配Action的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/header" }, method = { RequestMethod.GET }, headers = "Name")
	public String page4(@RequestHeader("User-Agent") String agent,
			@Value("#{systemProperties['java.vm.version']}") String version) {
		return "header info agent["+agent+"],syspro version["+version+"]";
	}

	/**
	 * 通过注解获取POST请求body内容的示例
	 * 
	 * @param postBody
	 * @return
	 */
	@RequestMapping(path = { "page/post" }, method = { RequestMethod.POST })
	public String page5(@RequestBody String postBody) {
		return "post body["+postBody+"]";
	}

	/**
	 * 参数转换的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/convert" }, method = { RequestMethod.GET })
	public String page6(@RequestParam("time") @DateTimeFormat(iso=ISO.DATE) Date time,
			Integer index) {
		return "params:time["+time+"] index["+index+"]";
	}

	/**
	 * 根据Content-Type匹配Action的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/json" }, method = { RequestMethod.GET }, produces = "application/json")
	public String page7() {
		return "received json request";
	}

	/**
	 * 根据Content-Type匹配Action的示例
	 * 
	 * @return
	 */
	@RequestMapping(path = { "page/cookie" }, method = { RequestMethod.GET })
	public String page8(@CookieValue("user") String user) {
		return "get cookie user["+user+"]";
	}

	/**
	 * 默认注入的参数
	 * 可注入的参数 {@link org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver#getDefaultArgumentResolvers}
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = { "page/in" })
	public String defaultInjectParam(WebRequest webRequest, ServletRequest sRequest, 
			HttpSession session, Principal pri, Locale locale, TimeZone tz, ZoneId zid, Reader reader,
			HttpMethod httpMethod, ServletResponse response,   Model model) {
		System.out.println("debug");
		return "use debug";
	}
	
	@RequestMapping(path = { "page/in11"})
	public String defaultInjectParam11(InputStream is) {
		System.out.println("debug");
		return "use debug";
	}
	
	@RequestMapping(path = { "page/in12"})
	public String defaultInjectParam12(OutputStream os) {
		System.out.println("debug");
		return "use debug";
	}
	
	@RequestMapping(path = { "page/in13"})
	public String defaultInjectParam13(Writer writer) {
		System.out.println("debug");
		return "use debug";
	}
	
	/**
	 * 自定义注入的参数
	 * 可注入的参数 {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#getDefaultArgumentResolvers}
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = { "page/in14" })
	public String defaultInjectParam13(MultipartRequest mRequest) {
		System.out.println("debug");
		return "use debug";
	}
	
	/**
	 * 自定义注入的参数
	 * 可注入的参数 {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#getDefaultArgumentResolvers}
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = { "page/in3" })
	public String defaultInjectParam(Map map, HttpEntity httpEntity,RequestEntity requestEntity,@RequestPart String part, Errors errors) {
		System.out.println("debug");
		return "use debug";
	}
}
