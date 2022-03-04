package web.practice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private String title;
    private String content;

    public Item(){
    }

    public Item(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
