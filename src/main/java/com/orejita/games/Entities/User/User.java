package com.orejita.games.Entities.User;

import java.util.Date;
import java.util.List;

import com.orejita.games.Entities.Comment.Comment;
import com.orejita.games.Entities.Rating.Rating;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private Date birthDate;
    private String username;
    private String password;
    private String avatar;

    @OneToOne(mappedBy = "user")
    private Collection collection;

    @OneToMany(mappedBy = "user")
    private List<UserGameStatic> gameStatic;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    
}
