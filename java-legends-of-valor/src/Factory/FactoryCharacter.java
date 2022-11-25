package Factory;

import Character.Hero;
import Character.Monster;

public class FactoryCharacter {

	public static Hero createHero(String heroName) {
		Hero hero = new Hero(heroName);
		return hero;
	}
	
//	public static Monster createMonster(Hero hero) {
//		
//	}
}
