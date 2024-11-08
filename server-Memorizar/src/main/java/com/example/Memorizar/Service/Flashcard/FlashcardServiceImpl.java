package com.example.Memorizar.Service.Flashcard;

import com.example.Memorizar.Entity.Flashcard;
import com.example.Memorizar.Entity.User;
import com.example.Memorizar.Respository.FlashcardRepository;
import com.example.Memorizar.Respository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService{

    private final FlashcardRepository flashcardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Flashcard createFlashcard(Long userId, Flashcard flashcard) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException("User not found!");
        }

        User user = optionalUser.get();
        flashcard.setUser(user);
        return flashcardRepository.save(flashcard);
    }

    @Override
    public List<Flashcard> getFlashcardsByUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException("User not found!");
        }
        return flashcardRepository.findByUserId(userId);
    }

    @Override
    public Optional<Flashcard> updateFlashcard(Long flashcardId, Flashcard flashcard) {
        Optional<Flashcard> optionalExistingFlashcard = flashcardRepository.findById(flashcardId);
        if(optionalExistingFlashcard.isEmpty()){
            throw new IllegalArgumentException("Flashcard not found!");
        }
        Flashcard existingFlashcard = optionalExistingFlashcard.get();
        existingFlashcard.setFront(flashcard.getFront());
        existingFlashcard.setBack(flashcard.getBack());
        return Optional.of(flashcardRepository.save(existingFlashcard));
    }

    @Override
    public void deleteFlashcard(Long flashcardId) {
        if(!flashcardRepository.existsById(flashcardId)){
            throw new IllegalArgumentException("Flashcard not found!");
        }
        flashcardRepository.deleteById(flashcardId);

    }
}
