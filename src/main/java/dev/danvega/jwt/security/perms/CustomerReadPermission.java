package dev.danvega.jwt.security.perms;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('SCOPE_customer.read')")
public @interface CustomerReadPermission {
}
