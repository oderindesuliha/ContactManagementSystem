
package validations;

import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.exceptions.UserException;

public class UserValidation {

    public static boolean validateUser(UserRegisterRequest registerRequest) {
        if (registerRequest == null) {
            throw new UserException("Registration request cannot be null");
        }
        validateNames(registerRequest.getFirstName(), registerRequest.getLastName());
        validateEmail(registerRequest.getEmail());
        validatePhoneNumber(registerRequest.getPhoneNumber());
        return false;
    }

    private static void validateNames(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty() || !firstName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserException("First name cannot be empty and contain letters,spaces and apostrophes");
        }
        if (lastName == null || lastName.trim().isEmpty() || !lastName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserException("Last name cannot be empty and contain letters,spaces and apostrophes");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new UserException("Email is required.....Enter a valid email address");
        }
    }

    public static void validatePhoneNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new UserException("Phone number is required");
        }
        if (number.startsWith("0")) {
            String phoneNumber = number.replaceAll("\\s", "").trim();
            if (!phoneNumber.matches("^0(70|80|81|90|91)[0-9]{8}$")) {
                throw new UserException("Please enter a valid Nigerian phone number");
            }
        }
        else if (number.startsWith("+234")) {
            String checkPhoneNumber = number.replaceAll("\\s", "").trim();

            if (!checkPhoneNumber.matches("^\\+234(70|80|81|90|91)[0-9]{8}$")) {
                throw new UserException("Please enter a valid Nigerian phone number");
            }
        } else {
            throw new UserException("Phone number must start with either 0 or +234");
        }
    }
}