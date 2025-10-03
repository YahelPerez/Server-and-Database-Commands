package model;

import java.util.List;

/**
 * Represents the Author Model.
 * This class serves as a template (POJO - Plain Old Java Object) to store
 * the information retrieved from the Google Scholar API.
 */
public class Author {

    // Private attributes to encapsulate the author's data.
    private String name;
    private String affiliations;
    private String email;
    private List<String> interests;
    private int citedBy;

    /**
     * Constructor to create a new Author object with all its data.
     * @param name The full name of the author.
     * @param affiliations The author's affiliations (e.g., university, company).
     * @param email The author's verified email.
     * @param interests A list of the author's research interests.
     * @param citedBy The total number of citations the author has received.
     */
    public Author(String name, String affiliations, String email, List<String> interests, int citedBy) {
        this.name = name;
        this.affiliations = affiliations;
        this.email = email;
        this.interests = interests;
        this.citedBy = citedBy;
    }

    // --- Getters and Setters ---
    // Public methods to access and modify the private attributes in a controlled way.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public int getCitedBy() {
        return citedBy;
    }

    public void setCitedBy(int citedBy) {
        this.citedBy = citedBy;
    }

    /**
     * Overridden toString method to get a string representation of the Author object.
     * This is very useful for testing and debugging purposes.
     * @return A formatted string with all the author's details.
     */
    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", affiliations='" + affiliations + '\'' +
                ", email='" + email + '\'' +
                ", interests=" + interests +
                ", citedBy=" + citedBy +
                '}';
    }
}
