package com.example.Memorizar.Controller.Auth;

import com.example.Memorizar.Entity.Flashcard;
import com.example.Memorizar.Service.Flashcard.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Memorizar/api/Flashcard")
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardService flashcardService;

    @PostMapping("/create")
    public ResponseEntity<Flashcard> createFlashcard(@RequestParam Long userId, @RequestBody Flashcard flashcard){
        Flashcard createdFlashcard = flashcardService.createFlashcard(userId, flashcard);
        return ResponseEntity.ok(createdFlashcard);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Flashcard>> getFlashcardsByUser(@PathVariable Long userId) {
        List<Flashcard> flashcards = flashcardService.getFlashcardsByUser(userId);
        return ResponseEntity.ok(flashcards);
    }

    @PutMapping("/update/{flashcardId}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long flashcardId, @RequestBody Flashcard flashcard) {
        Optional<Flashcard> updatedFlashcard = flashcardService.updateFlashcard(flashcardId, flashcard);
        return updatedFlashcard.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{flashcardId}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long flashcardId) {
        try {
            flashcardService.deleteFlashcard(flashcardId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
