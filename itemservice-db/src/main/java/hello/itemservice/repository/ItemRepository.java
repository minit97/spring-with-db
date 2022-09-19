package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    @Select("select id, item_name, price, quantity from item where id=#{id}")
    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

}
