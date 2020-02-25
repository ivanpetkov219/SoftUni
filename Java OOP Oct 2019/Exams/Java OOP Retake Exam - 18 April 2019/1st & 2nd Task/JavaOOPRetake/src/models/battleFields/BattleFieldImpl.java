package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Beginner;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {


    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {

        if(!attackPlayer.isDead() || !enemyPlayer.isDead()){
            throw new IllegalArgumentException("Player is dead!");
        }

        if(attackPlayer instanceof Beginner){
            giveBeginnerBonus(attackPlayer);
        }
        if(enemyPlayer instanceof Beginner){
            giveBeginnerBonus(enemyPlayer);
        }

        getBonusHealth(attackPlayer);

        getBonusHealth(enemyPlayer);

        while (!attackPlayer.isDead() && !enemyPlayer.isDead()){

            int attackerDamagePoints = getPlayerDamagePoints(attackPlayer);

            enemyPlayer.takeDamage(attackerDamagePoints);

            if (enemyPlayer.isDead()){
                break;
            }

            int enemyDamagePoints = getPlayerDamagePoints(enemyPlayer);

            attackPlayer.takeDamage(enemyDamagePoints);
        }

    }

    private void getBonusHealth(Player player) {

       int bonus = player.getCardRepository().getCards().stream().mapToInt(Card::getHealthPoints).sum();

       player.setHealth(player.getHealth() + bonus);
    }

    private int getPlayerDamagePoints(Player player) {
        int damagePoints = 0;

        for (Card card : player.getCardRepository().getCards()) {
            damagePoints += card.getDamagePoints();
        }
        return damagePoints;
    }

    private void giveBeginnerBonus(Player attackPlayer) {
        attackPlayer.setHealth(attackPlayer.getHealth() + 40);
        attackPlayer.getCardRepository().getCards().forEach(card -> card.setDamagePoints(card.getDamagePoints() + 30));
    }
}
