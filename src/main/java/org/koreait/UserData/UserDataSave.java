package org.koreait.UserData;


import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserDataSave {

    /**
     * 사용자가 입력한 요청 데이터로 아이디 등록 및 수정 처리
     * 요청 데이터 중에서 아이디가 있다면 중복 반환, 없다면 추가로 판단
     *
     * @param item
     */
    public void save(User item) {
        File file = new File("userdata.obj"); // 파일 객체 생성 - 파일 존재 여부에 따라 생성 혹은 데이터 덮어쓰기
        Map<String, User> data = load(); // 기존 데이터 로드

        String id = item.getId();

        LocalDateTime regDt = item.getRegDt();
        if (regDt == null) { // 최초 가입인 경우
            item.setRegDt(LocalDateTime.now());
        } else { // 회원정보 수정
            item.setModDt(LocalDateTime.now());
        }

        data.put(id, item);

        try (FileOutputStream fos = new FileOutputStream(file); // file 데이터 존재 여부에 따라 생성 혹은 덮어쓰기
             ObjectOutputStream oos = new ObjectOutputStream(fos)) { // 객체를 직렬화한 후 저장
            oos.writeObject(data); // 객체를 직렬화한 후 저장

        } catch (IOException e) {} // try구문 후 스트림 자동 종료 / 리로스 누수 방지 / 별도의 close() 호출 불필요
    }

    /**
     * 상품 정보 목록 파일에서 로드
     *
     * @return
     */
    // 파일이 존재하고 정상적으로 읽을 수 있을 경우 products.obj 에서 데이터를 읽어와 Map<Long, Product> 반환
    private Map<String, User> load() {
        File file = new File("userdata.obj");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file); // 파일에서 byte 데이터를 읽어들이는 스트림
                 ObjectInputStream oos = new ObjectInputStream(fis)) { // 직렬화된 객체 데이터를 읽어들일 수 있는 스트림
                Map<String, User> data = (Map<String, User>) oos.readObject(); // Map<>에 기존 데이터가 있을 시 추가 정보 호출
                return data; // Map<>형 data 반환
            } catch (Exception e) {} // try구문 후 스트림 자동 종료 / 리로스 누수 방지 / 별도의 close() 호출 불필요
        }

        return new HashMap<>(); // 파일이 존재하지 않거나 예외 발생시 새로운 빈 HashMap<> 반환
    }

}
