package com.kleverland.compras;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.http.ContentType;

@SpringBootTest
class ComprasApplicationTests {

	@Test
	void contextLoads() {
		
		String uriBase = "https://postman-echo.com/get";
		given()
		.relaxedHTTPSValidation()
		.param("foo1", "bar1")
		.param("foo2", "bar2")
		.when()
		.get(uriBase)
		.then()
		.statusCode(200) // O status http retornado foi 200
		.contentType(ContentType.JSON) // O response foi retornado no formato JSON
		.body("headers.host", is("postman-echo.com")) // A chave "host" possui exatamente o valor "postman-echo.com"
		.body("args.foo1", containsString("bar")); //A chave "foo1" contém o valor "bar"
		
	}

}
