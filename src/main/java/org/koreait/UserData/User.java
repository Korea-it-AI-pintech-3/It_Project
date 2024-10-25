package org.koreait.UserData;

import java.time.LocalDateTime;

class User {
    private String id;
    private String password;
    private String nickName;

    private LocalDateTime regDt; // 상품등록일시
    private LocalDateTime modDt; // 상품수정일시

    public User(String id, String password, String nickName) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
    }
    public User(String id) {setId(id);}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getPw() {return password;}
    public void setPw(String pw) {this.password = password;}

    public String getNickName() {return nickName;}
    public void setNickName(String nickName) {this.nickName = nickName;}


    public LocalDateTime getModDt() {
        return modDt;
    }
    public void setModDt(LocalDateTime modDt) {
        this.modDt = modDt;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }
    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }


    @Override
    public String toString() {
        String info = "Id: " + id + "\n";
        info += "Pw: " + password + "\n";
        info += "NickName: " + nickName + "\n";
        return info;
    }
}
