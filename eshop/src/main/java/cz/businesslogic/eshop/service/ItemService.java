package cz.businesslogic.eshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cz.businesslogic.eshop.dto.ItemDTO;
import cz.businesslogic.eshop.entity.Item;
import cz.businesslogic.eshop.exception.BadRequestException;
import cz.businesslogic.eshop.repository.ItemRepository;
import ma.glasnost.orika.MapperFacade;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private MapperFacade mapper;
	
	
	public List<ItemDTO> findAll() {
		return mapper.mapAsList(itemRepository.findAllFetch(), ItemDTO.class);
	}
	
	private ItemDTO findOneInner(int id) {
		return mapper.map(itemRepository.findOne(id), ItemDTO.class);
	}

	public ItemDTO findOne(int id) {
		return findOneInner(id);
	}

	public List<ItemDTO> getInRange(int min, int max) {
		return mapper.mapAsList(itemRepository.getItemsInRange((double)min, (double)max), ItemDTO.class);
	}
	
	public ItemDTO save(ItemDTO item) {
		return mapper.map(itemRepository.save(mapper.map(item, Item.class)), ItemDTO.class);
	}
	
	public void delete(int id) {
		try {
			itemRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Not allowed.");
		}
	}

	public Long getSize() {
		return itemRepository.getSize();
	}
}
