package application.view;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.glass.ui.View.Capability;

import application.Main;
import application.struct.RessourcesConstraints;
import application.struct.Switch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class constraintsResourceItemController {
	ObservableList<String> matchfields=FXCollections.observableArrayList("Ipsrc","Ipdst","portsrc","portdst","macsrc","macdst","ingressport");
	ObservableList<String> tables=FXCollections.observableArrayList();
	public static RessourcesConstraints rescons;
	static ObservableList<String> list_Constraints = FXCollections.observableArrayList();
	static int id;
	static String field;
	static int cap;
	@FXML
	private Label l;
	@FXML
	public  ComboBox<String> match;
	@FXML
	private ComboBox<String> tablid;
	@FXML
	private TextField capacity;

	@FXML
	private void initialize() {
		
		//System.out.println(constraintsController.nbrtable);
		//for(i=0;i<constraintsController.nbrtable-1;i++){
		//	tables.add(Integer.toString(i));
		//}
		//System.out.println(SwitchController.i);
		if(SwitchController.i==constraintsController.nbrtable && SwitchController.flag==0){
			closeConstraint();
		}
		else if (SwitchController.flag!=0){
			System.out.println(SwitchController.flag);
			tablid.setValue(SwitchController.tableId);
			match.setValue(SwitchController.matchfield);
			capacity.setText(SwitchController.entries);
			match.setItems(matchfields);
		}
		else if(SwitchController.i<constraintsController.nbrtable){
			 
		   tablid.setValue(Integer.toString(SwitchController.i));
		//match.setItems(matchfields);
	    //tablid.setItems(tables);}
		
		System.out.println("22");
		   match.setItems(matchfields); }
		//SwitchController.i++;
		
	}
	@FXML
	private void getTableId(){
		//System.out.println(tablid.getValue());
		id=Integer.parseInt(tablid.getValue());
		System.out.println(id);
		//id=SwitchController.i;
	}
	@FXML
	private void getMatchField(){
		
		field=match.getValue();
		
	}
	
	public void getCapacity(){
		System.out.println(capacity.getText());
		try{
		cap=Integer.parseInt(capacity.getText());
		}catch (NumberFormatException e) {
	   System.out.println("error");}
	}	
	@FXML
	private void submitConstraints() throws IOException{
		//SwitchController swt=new SwitchController();
		//System.out.println(this.getConstraints().toString());
		//List<String> list_constraints = new ArrayList<String>(Arrays.asList(this.getConstraints().toString().split("\\,")));
		//swt.setListview(list_constraints);
		//CompilerController comp=new CompilerController();
		//setListview(Arrays.asList(constraintsResourceItemController.getConstraints().toString()));
		//SwitchController.listConstraints.setItems(list_Constraints);
		RessourcesConstraints rc=new RessourcesConstraints();
		try{
			rc.setCapacity(Integer.parseInt(capacity.getText()));
			}catch (NumberFormatException e) {
		   l.setVisible(true);
		   return;
		   }
		//rc.setCapacity(Integer.parseInt(capacity.getText()));
		rc.setMatchfield(field);
		rc.setTableId(SwitchController.i);
		SwitchController.i++;
		if(SwitchController.flag!=0){			
			SwitchController.flag=0;
			RessourcesConstraints r=new RessourcesConstraints();
			try{
				r.setCapacity(Integer.parseInt(capacity.getText()));
				}catch (NumberFormatException e) {
					l.setVisible(true);
					return;
					}
			//r.setCapacity(Integer.parseInt(capacity.getText()));
			r.setMatchfield(field);
			r.setTableId(Integer.parseInt(SwitchController.tableId));
			CompilerController.c.modifyListview(Arrays.asList(r.toString()));
			CompilerController.s.modifyConstraint(Integer.parseInt(SwitchController.tableId),r.getClass().getName(),r);
			Main.cst.close();
			Main.swt.close();
			Main.switchShow();}
		else{
			
		//SwitchController.rc.setMatchfield(field);
		//SwitchController.rc.setTableId(SwitchController.i);
		
		//SwitchController.rc.setCapacity(Integer.parseInt(capacity.getText()));
		//System.out.println(CompilerController.s.toString());
		//System.out.println(rc.toString());
		CompilerController.c.setListview(Arrays.asList(rc.toString()));
		
		//System.out.println(CompilerController.s.toString());
		//CompilerController.s.addConstraints(getConstraints().getClass().getName(),getConstraints());
		//CompilerController.s.addConstraints(getConstraints().getClass().getName(),SwitchController.rc);
		CompilerController.s.addConstraints(rc.getClass().getName(),rc);
		
		
		
		//CompilerController.s.addConstraints(SwitchController.rc.getClass().getName(),SwitchController.rc);
		//System.out.println(getConstraints());
		//CompilerController.s.addConstraints(getConstraints());
		//System.out.println(CompilerController.s.toString());
		//Main.swt.close();
		//comp.goswitch();
		Main.cst.close();
		Main.swt.close();
		//Main.constraintShow();
		//Main.constraintPerformanceItems();
		Main.switchShow();
		Main.constraintShow();
		Main.constraintPerformanceItems();
		//Main.cst.close();
		//Main.constraintShow();
		//Main.constraintPerformanceItems();
		//Main.cst.close();
		//System.out.println(constraintsController.i);
		//SwitchController.i++;
		}
	}	
	@FXML
	private void  closeConstraint(){
		Main.cst.close();
	}
	//public static RessourcesConstraints getConstraints(){
		//rescons =new RessourcesConstraints(id,cap,field);
		//System.out.println(id);
		//SwitchController.rc.setTableId(id);
		//SwitchController.rc.setMatchfield(field);
		//SwitchController.rc.setCapacity()
	
		
		//return SwitchController.rc;

//}
	public static void modifyListview(List<String> constraint){
		//System.out.println(constraint.get(0));
		System.out.println(list_Constraints.get(0));
		list_Constraints.set(Integer.parseInt(constraint.get(0).split(",")[0].split("=")[1]),constraint.get(0).toString());
		System.out.println(list_Constraints);
	}
	public static void setListview(List<String> constraint){
		//ObservableList<String> list_Constraints=FXCollections.observableArrayList(); 
		list_Constraints.addAll(constraint);
	}
}
