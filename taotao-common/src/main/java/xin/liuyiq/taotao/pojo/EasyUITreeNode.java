package xin.liuyiq.taotao.pojo;

import java.io.Serializable;

public class EasyUITreeNode implements Serializable{

    private Long Id;
    private String text;
    private String state;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
