import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;

import java.awt.*;


/**
 * Created by mostafa7azem on 12/05/17.
 */
public class FileManagerGui extends GridPane {
private int x=0;
    public FileManagerGui(){
        FileManager fm = new FileManager();
    setRow(this);
    setCol(this);
    VBox hb= new VBox();
    Label l=new Label("File Manager");
    l.setFont(new Font("Ariel",24));
    BorderPane borderpane = new BorderPane();
    HBox Hb=new HBox();
    Label path=new Label("path : ");
    TextField textField=new TextField();
    textField.setMinWidth(500);
    Label permission=new Label("permission : ");
    TextField pertextField=new TextField();
    pertextField.setMinWidth(500);
    Label Output=new Label();
    Label status=new Label();
    TreeItem<String>root,makeDir;
    root =new TreeItem<>();
    root.setExpanded(true);
    makeBranch("List Files",root);
    makeDir=makeBranch("make/delete directories",root);
    makeBranch("make file",makeDir);
    makeBranch("make folder",makeDir);
    makeBranch("delete file",makeDir) ;
    makeBranch("delete folder",makeDir);
    makeBranch("change permissons",root);
    makeBranch(" create symbolic",root);
    TreeView tree =new TreeView<>(root);
    tree.setShowRoot(false);
        tree.setMaxHeight(200);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
         if(newValue!=null){
             String s = v.getValue().toString();
             if(s.contains("TreeItem [ value: List Files ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 Hb.getChildren().addAll(path,textField);
                 borderpane.setTop(Hb);
                 this.x=0;
             }if(s.contains("TreeItem [ value: make file ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 Hb.getChildren().addAll(path,textField);
                 borderpane.setTop(Hb);
                 this.x=1;
             }if(s.contains("TreeItem [ value: make folder ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 Hb.getChildren().addAll(path,textField);
                 borderpane.setTop(Hb);
                 this.x=2;
             }if(s.contains(" TreeItem [ value: delete file ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 Hb.getChildren().addAll(path,textField);
                 borderpane.setTop(Hb);
                 this.x=3;
             }if(s.contains("TreeItem [ value: delete folder ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 Hb.getChildren().addAll(path,textField);
                 borderpane.setTop(Hb);
                 this.x=4;
             }if(s.contains("TreeItem [ value: change permissons ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 VBox vb=new VBox();
                 HBox hb2=new HBox();
                 Hb.getChildren().addAll(path,textField);
                 hb2.getChildren().addAll(permission,pertextField);
                 permission.setText("permisssion");
                 vb.getChildren().addAll(Hb,hb2);
                 borderpane.setTop(vb);
                 this.x=5;
             }if(s.contains("TreeItem [ value:  create symbolic ]")){
                 borderpane.setTop(null);
                 borderpane.setCenter(null);
                 Hb.getChildren().clear();
                 VBox vb=new VBox();
                 HBox hb2=new HBox();
                 Hb.getChildren().addAll(path,textField);
                 permission.setText("link to");
                 hb2.getChildren().addAll(permission,pertextField);
                 vb.getChildren().addAll(Hb,hb2);
                 borderpane.setTop(vb);
                 this.x=6;
             }
         }

        });
    Button submit =new Button("start");
        submit.setOnMouseClicked(e->{
            if(this.x==0){
                borderpane.setCenter(null);
            Output.setText(fm.listDir(textField.getText().toString()));
            borderpane.setCenter(Output);
            }if(this.x==1){
                borderpane.setCenter(null);
                Output.setText(fm.mkFile(textField.getText().toString()));
                borderpane.setCenter(Output);
            }if(this.x==2){
            borderpane.setCenter(null);
            Output.setText(fm.mkFolder(textField.getText().toString()));
            borderpane.setCenter(Output);
        }if(this.x==3){
                borderpane.setCenter(null);
                Output.setText(fm.delFile(textField.getText().toString()));
                borderpane.setCenter(Output);
            }
            if(this.x==4){
                borderpane.setCenter(null);
                Output.setText(fm.delFolder(textField.getText().toString()));
                borderpane.setCenter(Output);
            }if(this.x==5){
                borderpane.setCenter(null);
                Output.setText(fm.changePermission(Integer.parseInt(textField.getText().toString()),pertextField.getText().toString()));
                borderpane.setCenter(Output);
            }if(this.x==6){
                borderpane.setCenter(null);
                Output.setText(fm.mkLink(textField.getText().toString(), pertextField.getText().toString()));
                borderpane.setCenter(Output);
            }
    });
    hb.getChildren().addAll(tree,status,submit);
    this.setConstraints(borderpane,1,1);
    this.setConstraints(hb,0,1);
    this.setConstraints(l, 0, 0);
    this.getChildren().addAll(l,hb,borderpane);

}
    private void setCol(GridPane gridPane)
    {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(80);
        gridPane.getColumnConstraints().addAll(columnConstraints,columnConstraints1);

    }
    private void setRow(GridPane gridPane)
    {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(20);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(80);
        gridPane.getRowConstraints().addAll(rowConstraints,rowConstraints1);
    }
    public TreeItem<String>makeBranch(String title,TreeItem<String>parent){
        TreeItem<String> item=new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }
}

/*
 tree.setOnMouseClicked((e) ->{
            String msg=  tree.getSelectionModel()
                    .selectedItemProperty().getValue().toString();
            if(msg!=null && this.x==0){
                hBox.getChildren().addAll(path,textField);
                this.setConstraints(borderpane,1,1);
                borderpane.setTop(hBox);
                this.x++;
                this.getChildren().add(borderpane);

            }else if(msg=="TreeItem [ value: change permissons ]"){
                System.out.print("fuck  os");

            }
            else{
                status.setText("plz choose an option or fill path");
            }
        });

 */

