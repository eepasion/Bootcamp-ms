package com.pragma.bootcamp;

import com.pragma.bootcamp.domain.usecase.BootcampUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BootcampApplicationTests {


	@Autowired
	private BootcampUseCase bootcampUseCase;

	@Test
	void contextLoads() {
		assertNotNull(bootcampUseCase);
	}

}
