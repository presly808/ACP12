package ua.artcode.week3.serializer.obj_xml;

/**
 * Created by Олександр on 29.02.2016.
 */
public class Author {

    private String author;



    public Author(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "" + author;
    }
}