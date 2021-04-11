package net.thetechstack.projectmybookcollection.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thetechstack.projectmybookcollection.collections.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Book {
    @Id private Integer bookId;
    private Integer goodreadsBookId;
	private Integer bestBookId;
	private Integer workId;
    private Integer booksCount;
	private String isbn;
    private String isbn13;
    private String authors;
    private String originalPublicationYear;
    private String originalTitle;
    private String title;
    private String languageCode;
    private Double averageRating;
    private Integer ratingsCount;
    private Integer workRatingsCount;
    private Integer workTextReviewsCount;
    @Column(name = "ratings_1")
    private Integer ratings1;
    @Column(name = "ratings_2")
    private Integer ratings2;
    @Column(name = "ratings_3")
    private Integer ratings3;
    @Column(name = "ratings_4")
    private Integer ratings4;
    @Column(name = "ratings_5")
    private Integer ratings5;
	private String imageUrl;
	private String smallImageUrl;

    @OneToMany(mappedBy = "book")
    private Set<Collection> collectedBooks = new HashSet<>();
}
