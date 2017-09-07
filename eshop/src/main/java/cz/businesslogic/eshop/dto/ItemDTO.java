package cz.businesslogic.eshop.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ItemDTO implements Serializable {
	
	private int ID;
	
	private String name;
	
	private Double price;
	
	private String description;
	
	private List<OrderedItemDTO> orderedItems;
}
