package cz.businesslogic.eshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cz.businesslogic.eshop.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	@Query("select i from Item i where i.price >= ?1 and i.price <= ?2") //HQL
	public List<Item> getItemsInRange(double min, double max);
	
	@Query(nativeQuery=true, value="SELECT COUNT(*) FROM item") //SQL
	public Long getSize();

	@Query("select i from Item i left join fetch i.orderedItems")
	public List<Item> findAllFetch();

	@Query("select i from Item i left join fetch i.orderedItems where i.id = ?1")
	public Item findById(int id);
	
}	
