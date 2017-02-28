/*
 * Copyright 2012-2015 the original author or authors.
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

package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dave Syer
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void home() {
        assertThat(new TestRestTemplate().getForObject("http://localhost:" + port,
                String.class)).isEqualTo("foobar");
    }

    @Test
    public void post() {
        assertThat(new TestRestTemplate().postForObject("http://localhost:" + port,
                "foo\nbar", String.class)).isEqualTo("[FOO][BAR]");
    }

    @Test
    public void foos() {
        assertThat(new TestRestTemplate()
                .getForObject("http://localhost:" + port + "/foos", String.class))
                        .isEqualTo("{\"value\":\"foo\"}{\"value\":\"bar\"}");
    }

}