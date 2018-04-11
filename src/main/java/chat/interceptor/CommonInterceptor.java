package chat.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * @author 李桥
 * 自定义拦截器，拦截请求，并进行登陆是否超时的验证
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);  

	/** 
	 * 在业务处理器处理请求之前被调用 
	 * 如果返回false 
	 *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
	 * 如果返回true 
	 *    执行下一个拦截器,直到所有的拦截器都执行完毕 
	 *    再执行被拦截的Controller 
	 *    然后进入拦截器链, 
	 *    从最后一个拦截器往回执行所有的postHandle() 
	 *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
	 * @throws IOException 
	 * @throws ServletException 
	 */  

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException{
		//响应类型
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,GET,OPTIONS,DELETE");
		//指定允许其它域名访问
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8888");
		//响应头
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
		response.setHeader("Access-Control-Max-Age", "3600");
		//默认情况下，跨域亲求不提供凭证（cookie，HTTP认证以及客户端SSL证明等），设置带请求凭证的时候需要指定这个字段
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许发送Cookie信息
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
		
		log.info("==============执行顺序: 1、preHandle================");  
		String requestUri = request.getRequestURI();
		//获取的根路径
		String contextPath = request.getContextPath();  
		//求求路径
		String url = requestUri.substring(contextPath.length());
		//静态资源放行
		if(url.endsWith(".js") || url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".html") 
				|| url.endsWith(".css")|| url.endsWith(".eot")|| url.endsWith(".svg")|| url.endsWith(".tff") || url.endsWith(".woff")){
			return true;
		}
		//打印
		log.info("requestUri:"+requestUri);  
		log.info("contextPath:"+contextPath);  
		log.info("url:"+url);  
		//不拦截连接（不校验），不需要登陆
		if("/userController/saveUser".compareTo(url)==0 || "/userController/checkLoginUser".compareTo(url)==0){
			return true;
		}
		HttpSession session = request.getSession(true);
		//82AE209A807B2F7775BD98792385A133
		//如果失效，用户在页面操作将会回到登陆页面，暂时没有解决跨域问题
//		if(session.getAttribute("user") == null){
//			response.setStatus(401);
//			return false;
//		}
		return true;
	} 

	/** 
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 
	 * 可在modelAndView中加入数据，比如当前时间 
	 */  
	@Override  
	public void postHandle(HttpServletRequest request,  
			HttpServletResponse response, Object handler,  
			ModelAndView modelAndView) throws Exception {  
		log.info("==============执行顺序: 2、postHandle================");  
	}  

	/** 
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */  
	@Override  
	public void afterCompletion(HttpServletRequest request,  
			HttpServletResponse response, Object handler, Exception ex)  
					throws Exception {  
		log.info("==============执行顺序: 3、afterCompletion================");  
	}  
}
