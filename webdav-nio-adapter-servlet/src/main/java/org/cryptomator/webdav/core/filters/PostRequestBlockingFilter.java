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
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.joining;

/**
 * Blocks all post requests.
 */
public class PostRequestBlockingFilter implements HttpFilter {

	private static final Logger LOG = LoggerFactory.getLogger(PostRequestBlockingFilter.class);
	private static final String POST_METHOD = "POST";

	@Override
	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (isPost(request)) {
			LOG.warn("Blocked POST request to {}", request.getRequestURI());
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} else {
			chain.doFilter(request, new FilteredResponse(response));
		}
	}

	private boolean isPost(HttpServletRequest request) {
		return POST_METHOD.equalsIgnoreCase(request.getMethod());
	}

	private static class FilteredResponse extends HttpServletResponseWrapper {

		public FilteredResponse(HttpServletResponse response) {
			super(response);
		}

		@Override
		public void addHeader(String name, String value) {
			if (isAllowHeader(name)) {
				super.setHeader(name, removePost(value));
			} else {
				super.addHeader(name, value);
			}
		}

		@Override
		public void setHeader(String name, String value) {
			if (isAllowHeader(name)) {
				super.setHeader(name, removePost(value));
			} else {
				super.setHeader(name, value);
			}
		}

		private String removePost(String value) {
			return stream(value.split("\\s*,\\s*")).filter(isEqual("POST").negate()).collect(joining(", "));
		}

		private boolean isAllowHeader(String name) {
			return "allow".equalsIgnoreCase(name);
		}
	}

}
