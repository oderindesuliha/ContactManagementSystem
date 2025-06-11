
package validations;

import com.contact.dtos.requests.UserRegisterRequest;
import com.contact.exceptions.UserException;

public class UserValidations {

    public static void validateUser(UserRegisterRequest registerRequest) {
        if (registerRequest == null) {
            throw new UserException("Registration request cannot be null");
        }
        validateNames(registerRequest.getFirstName(), registerRequest.getLastName());
        validateEmail(registerRequest.getEmail());
        validatePhoneNumber(registerRequest.getPhoneNumber());
    }

    private static void validateNames(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new UserException("First name is required");
        }
        if (!firstName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserException("First name must be between 2-50 characters and can only contain letters, spaces, hyphens, and apostrophes");
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new UserException("Last name is required");
        }
        if (!lastName.matches("^[A-Za-z\\s-']{2,50}$")) {
            throw new UserException("Last name must be between 2-50 characters and can only contain letters, spaces, hyphens, and apostrophes");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email is required");
        }
        if (!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new UserException("Enter a valid email address");
        }
    }

    private static void validatePhoneNumber(String number) {
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
            String checkphoneNumber = number.replaceAll("\\s", "").trim();
            if (!checkphoneNumber.matches("^\\+234(70|80|81|90|91)[0-9]{8}$")) {
                throw new UserException("Please enter a valid Nigerian phone number");
            }
        } else {
            throw new UserException("Phone number must start with either 0 or +234");
        }
    }
}