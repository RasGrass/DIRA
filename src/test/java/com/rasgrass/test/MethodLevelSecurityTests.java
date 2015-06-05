/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rasgrass.test;

import com.rasgrass.dira.Application;
import com.rasgrass.dira.security.SecurityUtils;
import com.rasgrass.dira.security.SecurityConfiguration;
import com.rasgrass.dira.repository.PositionRepository;
import com.rasgrass.dira.domain.Position;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Collection of test cases used to verify method-level security.
 *
 * @author Greg Turnquist
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, SecurityConfiguration.class })
public class MethodLevelSecurityTests {

	@Autowired PositionRepository positionRepository;

	@Before
	public void setUp() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void rejectsMethodInvocationsForNoAuth() {

		try {
			positionRepository.findAll();
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}

		try {
			positionRepository.save(new Position());
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}

		try {
			positionRepository.delete(1L);
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
	}

	@Test
	public void rejectsMethodInvocationsForAuthWithInsufficientPermissions() {

		SecurityUtils.runAs("system", "system", "ROLE_USER");

		positionRepository.findAll();

		try {
			positionRepository.save(new Position());
			fail("Expected a security error");
		} catch (AccessDeniedException e) {
			// expected
		}
		try {
			positionRepository.delete(1L);
			fail("Expected a security error");
		} catch (AccessDeniedException e) {
			// expected
		}
	}

	@Test
	public void allowsMethodInvocationsForAuthWithSufficientPermissions() {

		SecurityUtils.runAs("system", "system", "ROLE_USER", "ROLE_ADMIN");

		positionRepository.findAll();
		positionRepository.save(new Position());
		positionRepository.delete(1L);
	}
}
