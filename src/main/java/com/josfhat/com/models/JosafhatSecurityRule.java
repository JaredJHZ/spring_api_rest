package com.josfhat.com.models;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RUNTIME)
@PreAuthorize("hasRole('ROLE_IT') or hasRole('ROLE_Admin')")
@PostAuthorize("hasRole('ROLE_Admin')")
public @interface JosafhatSecurityRule {
	
}
