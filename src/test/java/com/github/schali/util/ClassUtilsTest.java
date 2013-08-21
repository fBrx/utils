package com.github.schali.util;

import junit.framework.Assert;

import org.junit.Test;

public class ClassUtilsTest {

	@Test
	public void testWhichNull() {
		Assert.assertNull(ClassUtils.which(null));
	}

	@Test
	public void testWhichThis() {
		Assert.assertNotNull(ClassUtils.which(this.getClass()));
	}

}
