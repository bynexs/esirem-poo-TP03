package com.blackjack;

import com.entity.BlackjackManager;
import com.entity.Card;
import com.entity.EnumSpecialCard;
import com.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;
    private Scene scene1, scene2, scene3, scene4;

    private TextField usernameField = new TextField();
    private TextField betField = new TextField();
    private Player player = new Player();
    private Player dealer = new Player();
    private List<Card> cards = new ArrayList<>();
    private VBox panelPlayer;
    private VBox panelDealer;

    @Override
    public void start(Stage primaryStage) {

        cards = BlackjackManager.suffle(BlackjackManager.CreationDeck());
        boolean sortie = false;
        dealer.setName("Robot");
        player.setBalance(1000);

        BlackjackManager.InitialisationGame(cards, player, dealer);

        this.primaryStage = primaryStage;

        // Page 1 - Buttons for settings, new game, and load game
        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10));
        gridPane1.setVgap(5);
        gridPane1.setHgap(5);

        Button newGameButton = new Button("Nouvelle partie");
        newGameButton.setOnAction(e -> showNewGamePage());
        gridPane1.add(newGameButton, 0, 0);

        Button loadGameButton = new Button("Charger partie");
        loadGameButton.setOnAction(e -> showPage("Charger partie"));
        gridPane1.add(loadGameButton, 0, 1);

        Button settingsButton = new Button("Paramètres");
        settingsButton.setOnAction(e -> showPage("Paramètres"));
        gridPane1.add(settingsButton, 0, 2);
        scene1 = new Scene(gridPane1, 300, 300);

        // Page 2 - Form for username and bet (Nouvelle partie)
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(10));
        gridPane2.setVgap(3);
        gridPane2.setHgap(5);

        gridPane2.add(new Label("Balance:" + String.valueOf(player.getBalance())), 3, 0);

        gridPane2.add(new Label("Nom d'utilisateur:"), 0, 2);
        gridPane2.add(usernameField, 1, 2);

        gridPane2.add(new Label("Mise:"), 0, 3);
        gridPane2.add(betField, 1, 3);

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> primaryStage.setScene(scene1));
        gridPane2.add(backButton, 0, 5);

        Button validateButton = new Button("Valider");
        validateButton.setOnAction(e -> showGame());
        gridPane2.add(validateButton, 3, 5);

        scene2 = new Scene(gridPane2, 450, 200);

        BlackjackManager.InitialisationGame(cards, player, dealer);

        GridPane gridPane3 = new GridPane();
        gridPane3.setVgap(3);
        gridPane3.setHgap(6);

        panelPlayer = new VBox();
        for (Card card : player.getHand().getCards()) {
            if (card.getSpecialCard() != EnumSpecialCard.CHIFFRE) {
                panelPlayer.getChildren().add(new Label(card.getSpecialCard().toString() + " de " + card.getCardType().toString() + " avec valeur " + card.getValue()));
            } else {
                panelPlayer.getChildren().add(new Label(card.getCardType().toString() + " avec valeur " + card.getValue()));
            }
        }

        panelDealer = new VBox();
        if (!dealer.getHand().getCards().isEmpty()) {
            panelDealer.getChildren().add(new Label(dealer.getHand().getCards().get(0).getCardType().toString() + " avec valeur " + dealer.getHand().getCards().get(0).getValue()));
        }
        gridPane3.add(new Label(player.getName()), 1, 0);
        gridPane3.add(panelPlayer, 0, 1, 3, 1);

        Separator separator = new Separator();
        gridPane3.add(separator, 0, 2, 3, 2);

        gridPane3.add(new Label("Banque"), 1, 3);
        gridPane3.add(panelDealer, 0, 4, 3, 4);

        Button pickButton = new Button("Repiocher");
        pickButton.setOnAction(e -> ShowPickCardPlayer());
        gridPane3.add(pickButton, 0, 5);

        Button terminateButton = new Button("Terminer");
        terminateButton.setOnAction(e -> {
            try {
                DealerPickCard();
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        gridPane3.add(terminateButton, 2, 5);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(33.33); // 33.33% de la largeur de la scène pour chaque colonne
        gridPane3.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(16.67); // 16.67% de la hauteur de la scène pour chaque ligne
        gridPane3.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints, rowConstraints, rowConstraints, rowConstraints);

        scene3 = new Scene(gridPane3, 500, 300);
        Label label = null;
        if ((player.getHand().getValue() > dealer.getHand().getValue() && player.getHand().getValue() <= 21) || (dealer.getHand().getValue() > 21)) {
            label = new Label("C'est Gagner");
        } else {
            label = new Label("C'est Perdu");
        }

        scene4 = new Scene(label, 500, 300);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Blackjack");
        primaryStage.show();
    }

    private void showNewGamePage() {
        usernameField.clear();
        betField.clear();
        primaryStage.setScene(scene2);
    }

    private void showGame() {
        player.setName(usernameField.getText());
        if (Integer.parseInt(betField.getText()) > 0 && Integer.parseInt(betField.getText()) <= player.getBalance()) {
            player.setBalance(player.getBalance() - Integer.parseInt(betField.getText()));
        }
        primaryStage.setScene(scene3);
        usernameField.clear();
        betField.clear();
    }

    private void ShowPickCardPlayer() {
        if (player.getHand().getValue() < 21) {
            BlackjackManager.PickCard(cards, player);
            if (player.getHand().getValue() >= 21) {
                ShowAllCard(player, dealer);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                    primaryStage.setScene(scene4);
                }));
                timeline.play();
                primaryStage.setScene(scene4);
            }
            // Mise à jour du panneau du joueur
            panelPlayer.getChildren().clear(); // Efface les anciennes cartes
            for (Card card : player.getHand().getCards()) {
                if (card.getSpecialCard() != EnumSpecialCard.CHIFFRE) {
                    panelPlayer.getChildren().add(new Label(card.getSpecialCard().toString() + " de " + card.getCardType().toString() + " avec valeur " + card.getValue()));
                } else {
                    panelPlayer.getChildren().add(new Label(card.getCardType().toString() + " avec valeur " + card.getValue()));
                }
            }
        } else {
            ShowAllCard(player, dealer);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                primaryStage.setScene(scene4);
            }));
            timeline.play();
            primaryStage.setScene(scene4);
        }
    }

    private void showPage(String title) {
        Label titleLabel = (Label) scene2.getRoot().getChildrenUnmodifiable().get(0);
        titleLabel.setText(title);
        primaryStage.setScene(scene2);
    }

    private void DealerPickCard() throws InterruptedException {
        while (dealer.getHand().getValue() <= 16) {
            dealer = BlackjackManager.PickCard(cards, dealer);
        }
        ShowAllCard(player, dealer);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            primaryStage.setScene(scene4);
        }));
        timeline.play();
    }

    public void ShowAllCard(Player player, Player dealer) {
        panelPlayer.getChildren().clear();
        panelDealer.getChildren().clear();

        for (Card card : player.getHand().getCards()) {
            if (card.getSpecialCard() != EnumSpecialCard.CHIFFRE) {
                panelPlayer.getChildren().add(new Label(card.getSpecialCard().toString() + " de " + card.getCardType().toString() + " avec valeur " + card.getValue()));
            } else {
                panelPlayer.getChildren().add(new Label(card.getCardType().toString() + " avec valeur " + card.getValue()));
            }
        }

        for (Card card : dealer.getHand().getCards()) {
            if (card.getSpecialCard() != EnumSpecialCard.CHIFFRE) {
                panelDealer.getChildren().add(new Label(card.getSpecialCard().toString() + " de " + card.getCardType().toString() + " avec valeur " + card.getValue()));
            } else {
                panelDealer.getChildren().add(new Label(card.getCardType().toString() + " avec valeur " + card.getValue()));
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
