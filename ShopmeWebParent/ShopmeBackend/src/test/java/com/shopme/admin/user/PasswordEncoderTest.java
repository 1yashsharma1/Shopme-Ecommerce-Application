package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void testEncodePassword() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String testPass = "yash1212";
	String encodedPass = encoder.encode(testPass);
	assertThat(encoder.matches(testPass, encodedPass)).isTrue();
    }

}
