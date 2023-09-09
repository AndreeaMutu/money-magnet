package com.example.moneymagnet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("My test name")
class MoneyMagnetApplicationTests {

	@Test
	@DisplayName("Test sum")
	void demo() {
		Assertions.assertEquals(2, 1+1, "Wrong sum");
	}

}
