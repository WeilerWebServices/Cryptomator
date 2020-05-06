/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.webdav.core.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface HttpFilter extends Filter {

	@Override
	default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			doFilterHttp((HttpServletRequest) request, (HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	default void init(FilterConfig filterConfig) throws ServletException {
		// no-op
	}

	@Override
	default void destroy() {
		// no-op
	}

	void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

}
