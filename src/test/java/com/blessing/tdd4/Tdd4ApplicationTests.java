package com.blessing.tdd4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import io.micrometer.core.annotation.TimedSet;

@SpringBootTest
class Tdd4ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void samplejunittest(){
		Assert.isTrue(true, "msg if false");
	}

}
