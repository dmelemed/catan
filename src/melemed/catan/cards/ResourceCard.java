package melemed.catan.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.NumberToken;
import melemed.catan.board.Resource;

public class ResourceCard {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceCard.class);

	private Resource resource;

	ResourceCard(Resource resource) {
		this.resource = resource;
	}
	
	public Resource getResource() {
		return resource;
	}
}