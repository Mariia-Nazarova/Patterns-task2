package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import jdk.jfr.ContentType;
import lombok.Value;
import ru.netology.testmode.test.AuthTest;

public class DataGenerator {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaserUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(logDetail.ALL)
            .build();

    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {

    }

    private static RegistrationDto sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("api/system/users")
                .then()
                .statusCode(200);
        return user;
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static class Registration {
        private Registration() {
        }

        public static AuthTest.RegistrationDto getUser(String status) {
            return new AuthTest.RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        public static AuthTest.RegistrationDto getRegisteredUser(String status) {
            return sendRequest(getUser(status));
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}