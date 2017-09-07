package cz.businesslogic.eshop.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cz.businesslogic.eshop.dto.ItemDTO;
import cz.businesslogic.eshop.exception.BadRequestException;
import cz.businesslogic.eshop.service.ItemService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api
@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Scheduled(fixedDelay=5_000)
	@CacheEvict(value="itemPriceRange", allEntries=true)
	public void clearCache() {
		log.info("Job every 5s - cache cleared");
	}
	
	
	@GetMapping("/getAll")
	public List<ItemDTO> findAll() {
		return itemService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> getById(@PathVariable int id) {
		ItemDTO itemDto = itemService.findOne( id );
		if (itemDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(itemDto);
	}
	
	@Cacheable("itemPriceRange")
	@GetMapping("/getPriceRange")
	public List<ItemDTO> getInRange(@RequestParam int min, @RequestParam int max) {
		return itemService.getInRange(min, max);
	}
	
	@PostMapping
	public ItemDTO insert(@RequestBody ItemDTO item) {
		return itemService.save(item);
	}
	
	@PutMapping("/{id}")
	public ItemDTO update(@PathVariable int id, @RequestBody ItemDTO item) {
		item.setID(id);
		return itemService.save(item);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		itemService.delete(id);
	}
	
	@GetMapping("/getSize")
	public Long getSize() {
		return itemService.getSize();
	}
	
	@ExceptionHandler
	public String handler(BadRequestException e) {
		return e.getMessage();
	}
	
	
}
