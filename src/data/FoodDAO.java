package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Food;

@Transactional
public class FoodDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Food> index(){
		String query = "Select f FROM Food f";
		System.out.println("Request for entire list was made");
		return em.createQuery(query, Food.class).getResultList();
	}
	
	public Food show(int id){
		return em.find(Food.class, id);
	}
	
	public Food create(Food food){
		em.persist(food);
		em.flush();
		return em.find(Food.class, food.getId());
	}
	
	public void delete(int id){
		Food delete = em.find(Food.class, id);
		em.remove(delete);
	}
	
	public Food update(int id, Food food){
		em.merge(food);
		 return em.find(Food.class, id);
	}
	
}
