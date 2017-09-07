package cz.businesslogic.eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ordereditem")
public class OrderedItem {

	@Id
	@Column(name="ordereditemid")
	private int id;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
}
