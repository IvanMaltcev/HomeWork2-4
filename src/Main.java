import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        try {
            verificationEnteredData("java_skypro_go", "D_1hWiKjjP_9",
                    "D_1hWiKjjP_9");
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void verificationEnteredData(String login, String password,
                                               String confirmPassword) {

        if (login == null || login.isEmpty() || password == null || password.isEmpty()
                || confirmPassword == null) {
            System.out.println("Логин или пароль не заполнены!");
            return;
        }

        String regex = "[A-Za-z0-9_]*";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            throw new WrongLoginException("Логин может содержать только " +
                    "латинские буквы, цифры и знак подчеркивания.");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Длина логина не должна превышать 20 символов.");
        }

        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new WrongPasswordException("Пароль может содержать только " +
                    "латинские буквы, цифры и знак подчеркивания.");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Длина пароля не должна превышать 20 символов!");
        }

        if (password.compareTo(confirmPassword) != 0) {
            throw new WrongPasswordException("Пароли не совпадают!");
        }
    }
}
