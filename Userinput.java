package sp;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class Userinput{

private static String tableName = "known";


public static String getTableName() {
	return tableName;
}

public static void setTableName(String tableName) {
	Userinput.tableName = tableName;
}
private static String tableName2 = "log";


public static String getTableName2() {
	return tableName2;
}

public static void setTableName2(String tableName2) {
	Userinput.tableName2 = tableName2;
}


}
