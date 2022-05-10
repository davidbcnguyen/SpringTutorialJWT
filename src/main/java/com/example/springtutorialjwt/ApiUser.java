package com.example.springtutorialjwt;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class ApiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "following",
        joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"))
    private List<ApiUser> followings;

    @ManyToMany(mappedBy = "followings")
    private List<ApiUser> followers;
}
