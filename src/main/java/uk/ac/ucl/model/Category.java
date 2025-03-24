package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<NotesRecord> notes;

    public Category(String name) {
        this.name = name;
        this.notes = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<NotesRecord> getNotes() { return notes; }
    public void addNote(NotesRecord note) { this.notes.add(note); }
    public void removeNote(NotesRecord note) { this.notes.remove(note); }
}