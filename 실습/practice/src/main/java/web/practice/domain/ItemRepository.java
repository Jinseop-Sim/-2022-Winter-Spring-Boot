package web.practice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository implements ItemRepo{

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L; // 하나씩 증가하는 id

    @Override
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long id){
        return store.get(id);
    }

    @Override
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long itemId, Item updatedItem){
        Item findItem = findById(itemId);
        findItem.setTitle(updatedItem.getTitle());
        findItem.setContent(updatedItem.getContent());
    }

    @Override
    public void clearStore(){
        store.clear();
    }

}
