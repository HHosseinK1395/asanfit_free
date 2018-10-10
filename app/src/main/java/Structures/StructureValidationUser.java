package Structures;

/**
 * Created by N550J on 2/15/2018.
 */

public class StructureValidationUser {
    String code;
    String token;

    public StructureValidationUser(String code, String token) {
        this.code = code;
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
