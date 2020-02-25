package core;

import common.ConstantMessages;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.BaseCard;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.Advanced;
import models.players.Beginner;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;


public class ManagerControllerImpl implements ManagerController {
        private PlayerRepository playerRepository;
        private CardRepository cardRepository;
        private Battlefield battlefield;

    public ManagerControllerImpl() {
        playerRepository = new PlayerRepositoryImpl();
        cardRepository = new CardRepositoryImpl();
        battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {
        Player player;
        if(type.equals("Beginner")){
            player = new Beginner(new CardRepositoryImpl(), username);
        }else {
            player = new Advanced(new CardRepositoryImpl(), username);
        }

        this.playerRepository.add(player);
       return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER, type, username);
    }

    @Override
    public String addCard(String type, String name) {
        BaseCard card;
        if(type.equals("Magic")){
            card = new MagicCard(name);
        }else {
            card = new TrapCard(name);
        }
        this.cardRepository.add(card);
        return  String.format(ConstantMessages.SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Player player = playerRepository.find(username);
        Card card = this.cardRepository.find(cardName);

        player.getCardRepository().add(card);
        return  String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attacker = playerRepository.find(attackUser);
        Player enemy = playerRepository.find(enemyUser);

        battlefield.fight(attacker, enemy);

        return  String.format(ConstantMessages.FIGHT_INFO, attacker.getHealth() + "", enemy.getHealth() + "");
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        for (Player player : playerRepository.getPlayers()) {
            int playerCardsCount = player.getCardRepository().getCount();
            sb.append(String.format(ConstantMessages.PLAYER_REPORT_INFO,
                    player.getUsername(),
                    player.getHealth(),
                    playerCardsCount));
            sb.append(System.lineSeparator());
            if(playerCardsCount != 0){
                for (Card card : player.getCardRepository().getCards()) {
                    sb.append(String.format(ConstantMessages.CARD_REPORT_INFO,
                            card.getName(),
                            card.getDamagePoints()));
                    sb.append(System.lineSeparator());
                }
            }
            sb.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
            sb.append(System.lineSeparator());


        }

        return  sb.toString().trim();
    }
}
