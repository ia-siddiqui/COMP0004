package uk.ac.ucl.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
  private List<NotesRecord> notes;
  private List<Category> categories;
  private ObjectMapper objectMapper;
  private static final String notesFile = "data" + File.separator + "notes.json";
  private static final String categoriesFile = "data" + File.separator + "categories.json";
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public Model() {
    this.notes = new ArrayList<>();
    this.categories = new ArrayList<>();
    this.objectMapper = new ObjectMapper();
    loadNotes(notesFile);
    loadCategories(categoriesFile);
  }

  public List<NotesRecord> getNotes() { return notes; }
  public void addNote(NotesRecord note) {
    NotesRecord newNote = new NotesRecord(
            note.noteName(),
            note.text(),
            note.url(),
            note.imagePath(),
            LocalDateTime.now().format(formatter),
            note.noteType()
    );
    notes.add(newNote);
    saveNotes(notesFile);
  }
  public void deleteNote(NotesRecord note) { notes.remove(note); saveNotes(notesFile); }
  public void updateNote(NotesRecord note) {
    notes.removeIf(n -> n.noteName().equals(note.noteName()));
    NotesRecord updatedNote = new NotesRecord(
            note.noteName(),
            note.text(),
            note.url(),
            note.imagePath(),
            LocalDateTime.now().format(formatter),
            note.noteType()
    );
    notes.add(updatedNote);
    saveNotes(notesFile);
  }

  public List<Category> getCategories() { return categories; }
  public void addCategory(Category category) { categories.add(category); saveCategories(categoriesFile); }
  public void deleteCategory(Category category) { categories.remove(category); saveCategories(categoriesFile); }
  public void updateCategory() { saveCategories(categoriesFile); }

  public List<NotesRecord> searchForNotes(String keyword) {
    return notes.stream()
            .filter(note -> note.noteName().contains(keyword) || note.text().contains(keyword))
            .collect(Collectors.toList());
  }

  public List<Category> searchForCategories(String keyword) {
    return categories.stream()
            .filter(category -> category.getName().contains(keyword) || category.getNotes().stream()
                    .anyMatch(note -> note.noteName().contains(keyword) || note.text().contains(keyword)))
            .collect(Collectors.toList());
  }

  public List<NotesRecord> sortNotesByTitle() {
    return notes.stream()
            .sorted(Comparator.comparing(NotesRecord::noteName))
            .collect(Collectors.toList());
  }

  public List<NotesRecord> sortNotesByDate() {
    return notes.stream()
            .sorted(Comparator.comparing(NotesRecord::date))
            .collect(Collectors.toList());
  }

  private void loadNotes(String NOTES_FILE) {
    try {
      File file = new File(NOTES_FILE);
      if (file.exists()) {
        NotesRecord[] loadedNotes = objectMapper.readValue(file, NotesRecord[].class);
        for (NotesRecord note : loadedNotes) {
          notes.add(note);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveNotes(String NOTES_FILE) {
    try {
      objectMapper.writeValue(new File(NOTES_FILE), notes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadCategories(String CATEGORIES_FILE) {
    try {
      File file = new File(CATEGORIES_FILE);
      if (file.exists()) {
        Category[] loadedCategories = objectMapper.readValue(file, Category[].class);
        for (Category category : loadedCategories) {
          categories.add(category);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveCategories(String CATEGORIES_FILE) {
    try {
      objectMapper.writeValue(new File(CATEGORIES_FILE), categories);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}