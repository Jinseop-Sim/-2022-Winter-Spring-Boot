package web.practice.domain;

import java.util.List;

public interface ItemRepo {
    Item save(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long id, Item updateParam);
    void clearStore();
}
