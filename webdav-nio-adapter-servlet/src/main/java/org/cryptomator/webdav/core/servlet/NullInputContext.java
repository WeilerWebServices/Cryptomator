/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.webdav.core.servlet;

import org.apache.jackrabbit.webdav.io.InputContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class NullInputContext implements InputContext {

	@Override
	public boolean hasStream() {
		return true;
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public long getModificationTime() {
		return 0;
	}

	@Override
	public String getContentLanguage() {
		return null;
	}

	@Override
	public long getContentLength() {
		return 0;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public String getProperty(String propertyName) {
		return null;
	}

}
