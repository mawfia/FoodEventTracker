package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.FoodDAO;
import entities.Food;

@RestController
public class FoodController {

@Autowired
private FoodDAO fooddao;


	@RequestMapping(value="ping", method=RequestMethod.GET)
	public String ping(){
		return "pong";
	}
	
	@RequestMapping(value="food", method=RequestMethod.GET)
	public List<Food> index(){
		return fooddao.index();
	}
	
	@RequestMapping(path="food/{id}", method=RequestMethod.GET)
	public Food show(@PathVariable int id){
		return fooddao.show(id);
	}
	
	@RequestMapping(path="food", method=RequestMethod.POST)
	public List<Food> create(@RequestBody String jsonFood){
		ObjectMapper mapper = new ObjectMapper();
		Food food = null;
		try{
			food = mapper.readValue(jsonFood,  Food.class);
		}catch (Exception e){
			e.printStackTrace();
		}
		//return fooddao.create(food);
		fooddao.create(food);
		return fooddao.index();
	}
	
	@RequestMapping(path="food/{id}", method=RequestMethod.DELETE)
	public List<Food> delete(@PathVariable int id){
		fooddao.delete(id);
		return fooddao.index();
	}
	
	@RequestMapping(value="food/{id}", method=RequestMethod.PUT)
	public List<Food> update(@PathVariable int id, @RequestBody String jsonFood){
		ObjectMapper mapper = new ObjectMapper();
		Food food = null;
		try{
			food = mapper.readValue(jsonFood,  Food.class);
		}catch (Exception e){
			e.printStackTrace();
		}
		//return fooddao.update(id, food);
		fooddao.update(id,  food);
		return fooddao.index();
	}

}
