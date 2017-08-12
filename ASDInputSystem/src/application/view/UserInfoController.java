package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.User;
import framework.Facade.UserAccessFacade;
import framework.Memento.CareTaker;
import framework.Memento.Originator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
//import storage.UserAccessFacade;

public class UserInfoController implements Initializable {
	@FXML
	private TextField id = null;
	
	@FXML
	private TextField lastName = null;

	@FXML
	private TextField firstName = null;
	
	@FXML
	private TextField address = null;

	@FXML
	private TextField gender = null;

	@FXML
	private TableView<User> table;

	@FXML
	private TableColumn<User, String> idTbl = new TableColumn("ID");

	@FXML
	private TableColumn<User, String> lastNameTbl = new TableColumn("Last Name");

	@FXML
	private TableColumn<User, String> firstNameTbl = new TableColumn("First Name");

	@FXML
	private TableColumn<User, String> addressTbl = new TableColumn("Address");

	@FXML
	private TableColumn<User, String> genderTbl = new TableColumn("Gender");

	private ObservableList<User> data;
	
	Originator<User> originator = new Originator();
	CareTaker careTaker = new CareTaker();
	
	private void saveState() {
		User user = new User(id.getText(),
				lastName.getText(),
				firstName.getText(),
				address.getText(),
				gender.getText());

		originator.setState(user);
		careTaker.add(originator.saveStateToMemenTo());
	}
	
	public void onLastNameKey(KeyEvent event) throws IOException {
		saveState();
	}
	public void onAddressKey(KeyEvent event) throws IOException {
		saveState();
	}
	public void onFirstNameKey(KeyEvent event) throws IOException {
		saveState();
	}
	public void onGenderKey(KeyEvent event) throws IOException {
		saveState();
	}
	public void onIdKey(KeyEvent event) throws IOException {
		saveState();
	}
	public void onLoad(ActionEvent event) throws IOException {
		User user = table.getSelectionModel().getSelectedItem();
		if (user != null) {
			id.setText(user.getId());
			lastName.setText(user.getLastName());
			firstName.setText(user.getFirstName());
			gender.setText(user.getGender());
			address.setText(user.getAddress());
		}


	}
	
	private void updateTable() {
		UserAccessFacade<User> userAccessFacade = new UserAccessFacade();
		data =  FXCollections.observableArrayList(
				userAccessFacade.readAll());
		table.getColumns().removeAll();
		table.setItems(data);
	}
	public void onSave(ActionEvent event) throws IOException {
			UserAccessFacade<User> userAccessFacade = new UserAccessFacade();
			if (userAccessFacade.saveData(new User(id.getText(),
					lastName.getText(),
					firstName.getText(),
					gender.getText(),
					address.getText()), id.getText())) {
				id.clear();
				lastName.clear();
				firstName.clear();
				gender.clear();
				address.clear();
				updateTable();
			}
	}
	public void onReturn(ActionEvent event) throws IOException {
		User user = (User)originator.getStateFromMemnto(careTaker.getLast());
		if (user != null) {
			id.setText(user.getId().toString());
			firstName.setText(user.getFirstName());
			lastName.setText(user.getLastName());
			address.setText(user.getAddress());
			gender.setText(user.getGender());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idTbl.setMinWidth(20);
		idTbl.setCellValueFactory(new PropertyValueFactory<>("id"));

		firstNameTbl.setMinWidth(100);
		firstNameTbl.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		lastNameTbl.setMinWidth(100);
		lastNameTbl.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		genderTbl.setMinWidth(50);
		genderTbl.setCellValueFactory(new PropertyValueFactory<>("gender"));

		addressTbl.setMinWidth(300);
		addressTbl.setCellValueFactory(new PropertyValueFactory<>("address"));

		table.getColumns().removeAll();
		table.getColumns().addAll(idTbl, firstNameTbl, lastNameTbl, genderTbl, addressTbl);
		updateTable();

	}
}
