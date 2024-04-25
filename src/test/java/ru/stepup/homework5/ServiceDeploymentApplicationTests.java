package ru.stepup.homework5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import ru.stepup.homework5.dto.*;
import ru.stepup.homework5.service.ProductServiceImpl;
import ru.stepup.homework5.service.RegisterServiceImpl;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServiceDeploymentApplication.class})
@AutoConfigureWebTestClient
@Testcontainers
class ServiceDeploymentApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	RegisterServiceImpl registerService;
	@Autowired
	ProductServiceImpl productService;
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
			.withInitScript("schema.sql");

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.datasource.username", postgres::getUsername);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;

	}

	@Test
	void registerServiceTest() {
		RegisterRequest registerRequest = new RegisterRequest(
				1,
				"02.001.005_45343_CoDowFF",
				"Клиентский",
				"500",
				"0021",
				"00",
				"13",
				"Клиент РЖД",
				"Регион РЖД",
				"00",
				"222"
		);

		RegisterResponse registerResponse = registerService.addProductRegister(registerRequest);

		Assertions.assertNotNull(registerResponse.accountId());

	}

	@Test
	void productServiceTest() {
		AgreesmentRequest agreesmentRequest = new AgreesmentRequest(
				"244",
				"27",
				"СМО",
				"270",
				"2024-15-04"
		);

		List<AgreesmentRequest> agreesmentRequestList= List.of(agreesmentRequest);
		ProductRequest productRequest = new ProductRequest(
				null,
				"НСО",
				"03.012.002",
				"03.012.002_47533_ComSoLd",
				"15",
				"001/2023_NSO",
				"2024-15-04",
				1,
				0,
				"0022",
				"800",
				"00",
				agreesmentRequestList
		);

		ProductResponse productResponse= productService.addProduct(productRequest);

		Assertions.assertNotNull(productResponse.instanceId());

	}

}
