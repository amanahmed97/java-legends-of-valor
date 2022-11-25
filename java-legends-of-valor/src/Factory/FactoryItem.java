package Factory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.ArmorEnum;
import Item.PotionEnum;
import Item.SpellEnum;
import Item.WeaponEnum;

public class FactoryItem {
	
	
	public static List<ArmorEnum> initializeArmory() {
		List<ArmorEnum> itemArmory = new ArrayList<ArmorEnum>();
		for(ArmorEnum ae : ArmorEnum.values()) {
			itemArmory.add(ae);
		}
		return itemArmory;
	}
	
	public static List<PotionEnum> initializePotion(){
		List<PotionEnum> itemPotion = new ArrayList<PotionEnum>();
		for (PotionEnum pe : PotionEnum.values()) {
			itemPotion.add(pe);
		}
		return itemPotion;
	}
	
	public static List<SpellEnum> initializeSpell(){
		List<SpellEnum> itemSpell = new ArrayList<SpellEnum>();
		for (SpellEnum pe : SpellEnum.values()) {
			itemSpell.add(pe);
		}
		return itemSpell;
	}
	
	public static List<WeaponEnum> initializeWeapon(){
		List<WeaponEnum> itemWeapon = new ArrayList<WeaponEnum>();
		for (WeaponEnum pe : WeaponEnum.values()) {
			itemWeapon.add(pe);
		}
		return itemWeapon;
	}
	
}
