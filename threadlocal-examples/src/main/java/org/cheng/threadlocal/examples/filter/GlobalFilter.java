package org.cheng.threadlocal.examples.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.cheng.threadlocal.examples.controller.TestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局过滤器
 * 
 * @author lucheng
 *
 */
@Slf4j
@Order(Integer.MIN_VALUE)
@WebFilter(filterName = "globalFilter", urlPatterns = "/*")
@Configuration
public class GlobalFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} finally {
			destroyGlobal();
		}
	}

	private void destroyGlobal() {
		log.info("-----------销毁资源--------------");
		TestController.LOCAL.remove();
	}
}
