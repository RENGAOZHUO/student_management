package com.example.student.account;
import com.example.student.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/test")
public class AccountTestController {

    private final AccountService accountService;

    public AccountTestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/no-transaction")
    public ResponseEntity<Void> testWithoutTransaction() {

        accountService.transferWithoutTransaction();

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transaction")
    public ResponseEntity<Void> testWithTransaction() {

        accountService.transferWithTransaction();

        return ResponseEntity.noContent().build();
    }
}