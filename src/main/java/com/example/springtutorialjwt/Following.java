package com.example.springtutorialjwt;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "following")
@Table(name = "following")
@Getter
@Setter
@NoArgsConstructor
@IdClass(FollowingKey.class)
public class Following {
    @Id
    private Long follower_id;
    @Id
    private Long followee_id;
}

class FollowingKey implements Serializable {
    private Long follower_id;
    private Long followee_id;
}