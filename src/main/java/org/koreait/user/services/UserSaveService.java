package org.koreait.user.services;


import org.koreait.global.BeanContainer;
import org.koreait.user.entities.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Map;

public class UserSaveService {

    /**
     * 회원 추가 및 수정
     *
     * @param user
     */
    public void save(User user) {
        UserInfoService service = BeanContainer.getBean(UserInfoService.class);
        Map<String, User> data = service.load();
        String id = user.getId();

        LocalDateTime today = LocalDateTime.now();
        if (data.containsKey(id)) { // 수정
            user.setModDt(today);
        } else { // 추가
            user.setRegDt(today);
        }

        data.put(id, user);

        try (FileOutputStream fos = new FileOutputStream("users.obj");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {}
    }
}
