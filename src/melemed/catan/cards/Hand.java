package melemed.catan.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.Resource;

public class Hand {

	private static final Logger logger = LoggerFactory.getLogger(Hand.class);

	List<Resource> cardsInHand;

	public Hand() {
		cardsInHand = new ArrayList<Resource>();
	}

	public boolean canBuyRoad() {
		return cardsInHand.contains(Resource.BRICK) && cardsInHand.contains(Resource.WOOD);
	}

	public boolean canBuySettlement() {
		return cardsInHand.contains(Resource.BRICK) && cardsInHand.contains(Resource.SHEEP)
				&& cardsInHand.contains(Resource.WHEAT) && cardsInHand.contains(Resource.WOOD);
	}

	public boolean canBuyCity() {
		return Collections.frequency(cardsInHand, Resource.WHEAT) >= 2
				&& Collections.frequency(cardsInHand, Resource.ORE) >= 3;
	}

	public boolean canBuyDevCard() {
		return cardsInHand.contains(Resource.ORE) && cardsInHand.contains(Resource.WHEAT)
				&& cardsInHand.contains(Resource.SHEEP);
	}

	public boolean spend(Resource... resources) {
		for (Resource resource : resources) {
			int index = cardsInHand.indexOf(resource);
			if (index != -1) {
				cardsInHand.remove(index);
			} else {
				return false;
			}
		}
		return true;
	}

	public void buyRoad() {
		spend(Resource.WOOD, Resource.BRICK);
	}

	public void buySettlement() {
		spend(Resource.WOOD, Resource.BRICK, Resource.SHEEP, Resource.WHEAT);
	}

	public void buyCity() {
		spend(Resource.ORE, Resource.ORE, Resource.ORE, Resource.WHEAT, Resource.WHEAT);
	}

	public void buyDevCard() {
		spend(Resource.ORE, Resource.WHEAT, Resource.SHEEP);
	}
	
	public List<Resource> getCardsInHand() {
		return cardsInHand;
	}

	public void addCard(Resource resource) {
		cardsInHand.add(resource);
	}
	
	public int size() {
		return cardsInHand.size();
	}
	
	public List<Resource> getAll(Resource resource) {
		List<Resource> resources = new ArrayList<Resource>();
		for(Resource card: cardsInHand) {
			if(card == resource) {
				resources.add(card);
			}
		}
		return resources;
	}
}
