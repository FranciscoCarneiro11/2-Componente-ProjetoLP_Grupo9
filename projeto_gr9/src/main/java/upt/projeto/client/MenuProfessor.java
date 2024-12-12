package upt.projeto.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuProfessor {

    private Stage primaryStage; 

    public MenuProfessor(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start(Stage menuProfStage) {
        menuProfStage.setTitle("Menu do Professor");

        Label welcomeLabel = new Label("Bem-vindo, Professor!");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button adicionarDisciplinaButton = new Button("Adicionar Disciplina");
        Button verDadosButton = new Button("Ver Dados Pessoais");
        Button verProfessoresButton = new Button("Ver Todos os Professores");
        Button criarQuizButton = new Button("Criar Quiz");
        Button verRespostasButton = new Button("Ver Respostas dos Alunos");
        Button logoutButton = new Button("Sair");

        logoutButton.setOnAction(e -> {
            menuProfStage.close(); 
            primaryStage.show(); 
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, adicionarDisciplinaButton, verDadosButton, verProfessoresButton, criarQuizButton, verRespostasButton, logoutButton);

        Scene scene = new Scene(layout, 1280, 720);
        menuProfStage.setScene(scene);
        menuProfStage.show();
    }
}
