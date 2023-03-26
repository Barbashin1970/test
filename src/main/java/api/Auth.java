package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Auth {

    private final static String CREATE_USER = "http://vmapp1-1.vdc1.tipay.ru:32003/realms/test/protocol/openid-connect/token";

    @Step("Авторизация пользователя")
    public static Response loginUser() {
        return given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", "billing")
                .formParam("client_secret", "ysGfDBPjL9OoJ8jOqrJMeyEFu7nnpbpZ")
                .formParam("username", "test.user")
                .formParam("password", "123123")
                .formParam("grant_type", "password")
                .when()
                .post(CREATE_USER);

    }

    @Step("Генерируем токен авторизации JWT")
    public static String getToken() {
        Response responseLogin = loginUser();
        return responseLogin.then().extract().body().path("access_token");
    }
}