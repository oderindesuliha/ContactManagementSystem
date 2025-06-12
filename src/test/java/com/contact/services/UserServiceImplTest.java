//@SpringBootTest
//public class UserServiceImplTest {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private Users users;
//
//    @BeforeEach
//    public void setUp() {
//        users.deleteAll();
//    }
//
//    @Test
//    public void testGenerateOtp_WithValidPhoneNumber_Successful() {
//        OtpRequest request = new OtpRequest();
//        request.setPhoneNumber("09076763421");
//
//        OtpResponse response = userService.generateOtp(request);
//
//        assertTrue(response.isValidPhoneNumber());
//        assertEquals("OTP generated successfully", response.getMessage());
//        assertEquals(1, users.count());
//
//        User user = users.findByPhoneNumber("09076763421");
//        assertNotNull(user);
//        assertNotNull(user.getOtpCode());
//        assertFalse(user.isVerified());
//    }
//
//    @Test
//    public void testGenerateOtp_WithRegisteredPhoneNumber_Failed() {
//        // First generate OTP and register user
//        OtpRequest initialRequest = new OtpRequest();
//        initialRequest.setPhoneNumber("09076763421");
//        OtpResponse initialResponse = userService.generateOtp(initialRequest);
//        User user = users.findByPhoneNumber("09076763421");
//
//        // Register user with the generated OTP
//        UserRegisterRequest registerRequest = new UserRegisterRequest();
//        registerRequest.setFirstName("Bola");
//        registerRequest.setLastName("Oyetola");
//        registerRequest.setEmail("b.oyetola@gmail.com");
//        registerRequest.setPhoneNumber("09076763421");
//        registerRequest.setOtpCode(user.getOtpCode());
//        userService.register(registerRequest);
//
//        // Try to generate OTP for same number
//        OtpRequest request = new OtpRequest();
//        request.setPhoneNumber("09076763421");
//        OtpResponse response = userService.generateOtp(request);
//
//        assertFalse(response.isValidPhoneNumber());
//        assertEquals("Phone number already registered", response.getMessage());
//    }
//
//    @Test
//    public void testRegisterUser_WithValidOtp_Successful() {
//        // Generate OTP first
//        OtpRequest otpRequest = new OtpRequest();
//        otpRequest.setPhoneNumber("09076763421");
//        userService.generateOtp(otpRequest);
//        User user = users.findByPhoneNumber("09076763421");
//        String generatedOtp = user.getOtpCode();
//
//        UserRegisterRequest request = new UserRegisterRequest();
//        request.setFirstName("Bola");
//        request.setLastName("Oyetola");
//        request.setEmail("b.oyetola@gmail.com");
//        request.setPhoneNumber("09076763421");
//        request.setOtpCode(generatedOtp);
//
//        UserRegisterResponse response = userService.register(request);
//
//        assertEquals("User registered successfully", response.getMessage());
//        User registeredUser = users.findByPhoneNumber("09076763421");
//        assertNotNull(registeredUser);
//        assertEquals("Bola", registeredUser.getFirstName());
//        assertEquals("Oyetola", registeredUser.getLastName());
//        assertEquals("b.oyetola@gmail.com", registeredUser.getEmail());
//        assertTrue(registeredUser.isVerified());
//    }
//
//    @Test
//    public void testRegisterUser_WithInvalidOtp_Failed() {
//        // Generate OTP first
//        OtpRequest otpRequest = new OtpRequest();
//        otpRequest.setPhoneNumber("09076763421");
//        userService.generateOtp(otpRequest);
//
//        UserRegisterRequest request = new UserRegisterRequest();
//        request.setFirstName("Bola");
//        request.setLastName("Oyetola");
//        request.setEmail("b.oyetola@gmail.com");
//        request.setPhoneNumber("09076763421");
//        request.setOtpCode("invalid_otp");
//
//        UserRegisterResponse response = userService.register(request);
//
//        assertEquals("Invalid or already used OTP", response.getMessage());
//        User user = users.findByPhoneNumber("09076763421");
//        assertFalse(user.isVerified());
//    }
//
//    @Test
//    public void testRegisterUser_WithDuplicateEmail_Failed() {
//        // Register first user
//        registerUserWithOtp("09076763421", "b.oyetola@gmail.com");
//
//        // Try to register second user with same email
//        OtpRequest otpRequest = new OtpRequest();
//        otpRequest.setPhoneNumber("08012345678");
//        userService.generateOtp(otpRequest);
//        User secondUser = users.findByPhoneNumber("08012345678");
//
//        UserRegisterRequest request = new UserRegisterRequest();
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setEmail("b.oyetola@gmail.com"); // Same email
//        request.setPhoneNumber("08012345678");
//        request.setOtpCode(secondUser.getOtpCode());
//
//        UserRegisterResponse response = userService.register(request);
//
//        assertEquals("Email already exists", response.getMessage());
//    }
//
//    @Test
//    public void testLoginUser_WithValidCredentials_Successful() {
//        // Register user first
//        String email = "b.oyetola@gmail.com";
//        String password = "password123";
//        User registeredUser = registerUserWithOtp("09076763421", email);
//        registeredUser.setPassword(password);
//        users.save(registeredUser);
//
//        UserLoginRequest request = new UserLoginRequest();
//        request.setEmail(email);
//        request.setPassword(password);
//
//        UserLoginResponse response = userService.login(request);
//
//        assertEquals("Login successful", response.getMessage());
//    }
//
//    @Test
//    public void testLoginUser_WithInvalidCredentials_Failed() {
//        // Register user first
//        String email = "b.oyetola@gmail.com";
//        User registeredUser = registerUserWithOtp("09076763421", email);
//        registeredUser.setPassword("password123");
//        users.save(registeredUser);
//
//        UserLoginRequest request = new UserLoginRequest();
//        request.setEmail(email);
//        request.setPassword("wrong_password");
//
//        UserLoginResponse response = userService.login(request);
//
//        assertEquals("Invalid email, password, or unverified account", response.getMessage());
//    }
//
//    // Helper method to register a user with OTP
//    private User registerUserWithOtp(String phoneNumber, String email) {
//        OtpRequest otpRequest = new OtpRequest();
//        otpRequest.setPhoneNumber(phoneNumber);
//        userService.generateOtp(otpRequest);
//        User user = users.findByPhoneNumber(phoneNumber);
//
//        UserRegisterRequest registerRequest = new UserRegisterRequest();
//        registerRequest.setFirstName("Bola");
//        registerRequest.setLastName("Oyetola");
//        registerRequest.setEmail(email);
//        registerRequest.setPhoneNumber(phoneNumber);
//        registerRequest.setOtpCode(user.getOtpCode());
//        userService.register(registerRequest);
//
//        return users.findByPhoneNumber(phoneNumber);
//    }
//}