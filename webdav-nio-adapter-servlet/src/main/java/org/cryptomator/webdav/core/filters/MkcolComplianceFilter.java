/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.webdav.core.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responds with status code 415, if an attempt is made to create a collection with a body.
 * 
 * See https://tools.ietf.org/html/rfc2518#section-8.3.1:
 * "If the server receives a MKCOL request entity type it does not support or understand
 * it MUST respond with a 415 (Unsupported Media Type) status code."
 */
public class MkcolComplianceFilter implements HttpFilter {

	private static final Logger LOG = LoggerFactory.getLogger(MkcolComplianceFilter.class);
	private static final String METHOD_MKCOL = "MKCOL";
	private static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";

	@Override
	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean hasBody = request.getContentLengthLong() > 0 || request.getHeader(HEADER_TRANSFER_ENCODING) != null;
		if (METHOD_MKCOL.equalsIgnoreCase(request.getMethod()) && hasBody) {
			LOG.warn("Blocked invalid MKCOL request to {}", request.getRequestURI());
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "MKCOL with body not supported.");
		} else {
			chain.doFilter(request, response);
		}
	}

}
