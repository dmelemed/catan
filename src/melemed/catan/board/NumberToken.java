package melemed.catan.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum NumberToken {

	ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE;

	private static final Logger logger = LoggerFactory.getLogger(NumberToken.class);

	
	public String getImgUrl() {
		switch (this) {
		case TWO:
			return "img/num2.png";
		case THREE:
			return "img/num3.png";
		case FOUR:
			return "img/num4.png";
		case FIVE:
			return "img/num5.png";
		case SIX:
			return "img/num6.png";
		case SEVEN:
			return "img/num7.png";
		case EIGHT:
			return "img/num8.png";
		case NINE:
			return "img/num9.png";
		case TEN:
			return "img/num10.png";
		case ELEVEN:
			return "img/num11.png";
		case TWELVE:
			return "img/num12.png";

		default:
			return "img/num7.png";
		}

	}

	public int getProbabilityDots() {
		switch (this) {
		case TWO:
		case TWELVE:
			return 1;
		case THREE:
		case ELEVEN:
			return 2;
		case FOUR:
		case TEN:
			return 3;
		case FIVE:
		case NINE:
			return 4;
		case SIX:
		case EIGHT:
			return 5;
		case SEVEN:
			return 6;
		default:
			return 0;
		}
	}
}
