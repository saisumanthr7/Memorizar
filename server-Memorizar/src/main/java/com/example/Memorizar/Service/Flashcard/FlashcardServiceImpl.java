package com.example.Memorizar.Service.Flashcard;

import com.example.Memorizar.Entity.Flashcard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardServiceImpl implements FlashcardService{
    @Override
    public Flashcard createFlashcard(Long userId, Flashcard flashcard) {
        return null;
    }

    @Override
    public List<Flashcard> getFlashcardsByUser(Long userId) {
        return null;
    }

    @Override
    public Optional<Flashcard> updateFlashcard(Long flashcardId, Flashcard flashcard) {
        return Optional.empty();
    }

    @Override
    public void deleteFlashcard(Long flashcardId) {

    }
}
