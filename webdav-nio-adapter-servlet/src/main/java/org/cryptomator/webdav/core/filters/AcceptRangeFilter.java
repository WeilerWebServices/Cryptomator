/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.webdav.core.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Adds an <code>Accept-Range: bytes</code> header to all <code>GET</code> requests.
 */
public class AcceptRangeFilter implements HttpFilter {

	private static final String METHOD_GET = "GET";
	private static final String HEADER_ACCEPT_RANGES = "Accept-Ranges";
	private static final String HEADER_ACCEPT_RANGE_VALUE = "bytes";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// no-op
	}

	@Override
	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (METHOD_GET.equalsIgnoreCase(request.getMethod())) {
			response.addHeader(HEADER_ACCEPT_RANGES, HEADER_ACCEPT_RANGE_VALUE);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// no-op
	}

}
