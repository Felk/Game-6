package game6.core.combat;

import de.nerogar.util.Vector3f;

public class FightingObject {

	private int maxHealth;
	private int health;
	private float reach;

	private Vector3f position;
	private FightingObject target;

	private DieEvent dieEvent;
	private AttackEvent attackEvent;

	public FightingObject(int maxHealth, Vector3f position, DieEvent dieEvent) {
		this.health = maxHealth;
		this.position = position;
		this.dieEvent = dieEvent;
	}

	public void update() {
		if (attackEvent != null && target != null && target.getPosition().subtracted(getPosition()).getSquaredValue() <= reach * reach) {
			if (target.isDead()) target = null;
			attackEvent.onAttack(target);
		}
	}

	public void heal(int healAmount) {
		health += healAmount;

		if (health > maxHealth) health = maxHealth;
	}

	public void damage(int damage) {
		health -= damage;

		if (isDead()) {
			dieEvent.onDie();
		}
	}

	public boolean isDead() {
		return health <= 0;
	}

	public int getMaxhealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setTarget(FightingObject target) {
		this.target = target;
	}

	public FightingObject getTarget() {
		return target;
	}

	public void setReach(float reach) {
		this.reach = reach;
	}

	public float getReach() {
		return reach;
	}

	public void setAttackEvent(AttackEvent attackEvent) {
		this.attackEvent = attackEvent;
	}

	public AttackEvent getAttackEvent() {
		return attackEvent;
	}

}
