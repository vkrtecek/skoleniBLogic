package cz.businesslogic.eshop.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderedItemDTO implements Serializable {

	private int id;
	
	private int quantity;
}
