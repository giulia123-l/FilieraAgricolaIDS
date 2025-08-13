package controller;

import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.ApproveRequest;
import com.example.filiera.dto.RejectRequest;
import com.example.filiera.dto.UserResponse;
import com.example.filiera.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Admin")
@RequestMapping("/api/admin/users")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Elenca gli utenti in attesa di approvazione")
    @GetMapping("/pending")
    public ResponseEntity<List<UserResponse>> pending() {
        var list = userService.listPending().stream().map(UserService::toResponse).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Approva un utente in attesa, assegnando il ruolo")
    @PostMapping("/{id}/approve")
    public ResponseEntity<UserResponse> approve(@PathVariable("id") Long id, @Valid @RequestBody ApproveRequest request) {
        Utente u = userService.approve(id, request.ruoloAssegnato);
        return ResponseEntity.ok(UserService.toResponse(u));
    }

    @Operation(summary = "Rifiuta una registrazione con motivazione")
    @PostMapping("/{id}/reject")
    public ResponseEntity<UserResponse> reject(@PathVariable("id") Long id, @Valid @RequestBody RejectRequest request) {
        Utente u = userService.reject(id, request.motivazione);
        return ResponseEntity.ok(UserService.toResponse(u));
    }
}
