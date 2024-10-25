package org.koreait.user.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private String id;
    private String password;
    private String nickname;

    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
