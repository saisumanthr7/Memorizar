package com.example.Memorizar.Service.Flashcard;

import com.example.Memorizar.Entity.Flashcard;

import java.util.List;
import java.util.Optional;

public interface FlashcardService {


    Flashcard createFlashcard(Long userId, Flashcard flashcard);
    List<Flashcard> getFlashcardsByUser(Long userId);
    Optional<Flashcard> updateFlashcard(Long flashcardId, Flashcard flashcard);

    void deleteFlashcard(Long flashcardId);
}
