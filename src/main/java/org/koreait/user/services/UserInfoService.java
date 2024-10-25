package org.koreait.user.services;

import org.koreait.user.entities.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class UserInfoService {


    /**
     * 회원 한명 조회
     *
     * @param id
     * @return
     */
    public User getUser(String id) {

        return getUsers().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * 회원 목록 조회
     *
     * @return
     */
    public List<User> getUsers() {
        Map<String, User> data = load();

        Collection<User> items = data == null ? Collections.EMPTY_LIST : data.values();

        return new ArrayList<>(items);
    }

    /**
     * 회원 데이터 파일에서 조회
     *
     * @return
     */
    public Map<String, User> load() {
        File file = new File("users.obj");
        if (file.exists()) {

            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                return (Map<String, User>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {}
        }

        return new HashMap<>();
    }

}
