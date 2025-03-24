package uk.ac.ucl.model;

public record NotesRecord(
        String noteName,
        String text,
        String url,
        String imagePath,
        String date,
        String noteType)
{
    // Constructor
    public NotesRecord {
        if (noteName == null || (text == null && url == null && imagePath == null) || date == null) {
            throw new IllegalArgumentException("Title, text, and date cannot be null");
        }
    }
}