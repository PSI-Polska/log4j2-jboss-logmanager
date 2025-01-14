/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2023 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.logmanager.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.apache.logging.log4j.spi.LoggerContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class LoggerContextTestCase extends AbstractTestCase {

    @Test
    public void testHasLogger() {
        final LoggerContext loggerContext = LogManager.getContext();
        final Logger logger = LogManager.getFormatterLogger(LoggerTestCase.class);
        Assertions.assertFalse(loggerContext.hasLogger("org.jboss.logmanager"));
        Assertions.assertFalse(loggerContext.hasLogger(logger.getName()));
        Assertions.assertTrue(loggerContext.hasLogger(logger.getName(), StringFormatterMessageFactory.INSTANCE));
        Assertions.assertTrue(loggerContext.hasLogger(logger.getName(), StringFormatterMessageFactory.class));
    }

    @Test
    public void testExternalContext() {
        final Object externalContext = new Object();
        final LoggerContext loggerContext = LogManager.getContext(LoggerContextTestCase.class.getClassLoader(), true, externalContext);
        Assertions.assertEquals(externalContext, loggerContext.getExternalContext());
    }
}
