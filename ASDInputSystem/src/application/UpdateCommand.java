package application;

import framework.command.Command;
import javafx.scene.control.Alert;

public class UpdateCommand  implements Command {
    @Override
    public void execute() {
        new Alert(Alert.AlertType.INFORMATION, "Data is recorded").showAndWait();
    }
}
