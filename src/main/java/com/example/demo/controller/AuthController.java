
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          JwtTokenProvider tokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User registered = userService.register(user);
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        Authentication auth =
                new UsernamePasswordAuthenticationToken(user.getEmail(), null);

        String token = tokenProvider.generateToken(
                auth,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        LoginResponse response =
                new LoginResponse(token, user.getId(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(response);
    }
} 