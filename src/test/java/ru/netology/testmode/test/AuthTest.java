package ru.netology.testmode.test;

import lombok.Value;

public class AuthTest {


    public static String getRandomLogin(){
        return faker.name().username();
    }

    public static String getRandomPassword(){
        return faker.internet().password();
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        public static RegistrationDto getRegisteredUser(String status) {
            return sendRequest(getUser(status));
        }
    }

    @Value
    public static class RegistrationDto{
            String login;
            String password;
            String status;
        }
}
