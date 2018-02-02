package mainpack;

import tables.Costumer;
import tables.Purchase;
import tables.PurchaseOrder;
import tables.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TableMaker {

    private String propPath = "C:\\Users\\MaybeNull\\IdeaProjects\\Shoestore\\src\\mainpack\\prop.properties";
    private String username, password, connection2DB;

    public TableMaker() {
        setupProperties();
    }

    public List<PurchaseOrder> makeOrderList() {

        List<PurchaseOrder> temp = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(connection2DB, username, password);
                Statement stmnt = con.createStatement();
                PreparedStatement pst2 = con.prepareStatement("select id, shoeid, orderid from sale where ? = orderid;")
        ){
            ResultSet rs = stmnt.executeQuery(
"select purchase_order.id as id, purchase_order.purchaseid as pid, purchase_order.purchase_date as date, count(sale.orderid) as Sale from purchase_order join sale ON purchase_order.id = sale.orderid group by purchase_order.id;");
//listan AllOrders
            while (rs.next()){
                int id = rs.getInt("id");
                int pID = rs.getInt("pid");
                String d = rs.getString("date");
                PurchaseOrder po = new PurchaseOrder(id, pID, d);
                int antalSale = rs.getInt("Sale");
                temp.add(po);
//listan för allSales i Order
                if(antalSale>0){
                    pst2.setInt(1, id);
                    ResultSet rs2 = pst2.executeQuery();
                    for (int i = 0; i < antalSale; i++) {
                        rs2.next();
                        Sale sale = new Sale(rs2.getInt("id"), rs2.getInt("shoeid"), rs2.getInt("orderid"));
                        po.setOrderSale(sale);
                    }
                    rs2.close();
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return temp;
    }

    public List<Costumer> makeCostumerList() {

        List<Costumer> temp = new ArrayList<>();

        try (
            Connection con = DriverManager.getConnection(connection2DB, username, password);
            Statement stmnt = con.createStatement();
            PreparedStatement pst2 = con.prepareStatement("select id, costumerid from purchase where ? = costumerid;")
                ){
            ResultSet rs = stmnt.executeQuery(
"select costumer.id, costumer.firstname, costumer.lastname, count(purchase.id) as purchase from costumer join purchase ON costumer.id = purchase.costumerid GROUP BY costumer.id;");
//listan AllCostumers
            while (rs.next()){
                int id = rs.getInt("id");
                String f = rs.getString("firstname");
                String l = rs.getString("lastname");
                Costumer c = new Costumer(id, f, l);
                int antalPurchase = rs.getInt("purchase");
                temp.add(c);
//listan för allPurchases i costumer
                pst2.setInt(1, id);
                ResultSet rs2 = pst2.executeQuery();
                if(antalPurchase>0){
                    for (int i = 0; i < antalPurchase; i++) {
                        rs2.next();
                        Purchase purchase = new Purchase(rs2.getInt("id"), rs2.getInt("costumerid"));
                        c.setCostumerPurchase(purchase);
                    }
                    rs2.close();
                }

            }
        } catch (SQLException e) { e.printStackTrace(); }

        return temp;
    }

    private void setupProperties() {

        Properties p = new Properties();
        try {
            p.load(new FileInputStream(propPath));
            connection2DB=p.getProperty("connection2DB");
            username=p.getProperty("username");
            password=p.getProperty("password");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<Shoe> makeShoeList() {

        List<Shoe> temp = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(connection2DB, username, password);
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery(
"select id, size, price, color, brand, stockAmount from shoe;")
        ){

//listan AllShoes
            while (rs.next()){
                int id = rs.getInt("id");
                int s = rs.getInt("size");
                int p = rs.getInt("price");
                String c = rs.getString("color");
                String b = rs.getString("brand");
                int sa = rs.getInt("stockAmount");
                Shoe shoe = new Shoe(id, s , p , c, b, sa);
                temp.add(shoe);
                }

        } catch (SQLException e) { e.printStackTrace(); }

        return temp;

    }

    public List<Category> makeCategoryList() {

        List<Category> temp = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(connection2DB, username, password);
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery(
                        "select id, name from category")
        ){

//listan AllCategories
            while (rs.next()){
                int id = rs.getInt("id");
                String n = rs.getString("name");
                Category cat = new Category(id, n);
                temp.add(cat);
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return temp;
    }

    public Map<Category, List<Shoe>> makePairMap(List<Category> clist, List<Shoe> slist) {

        Map<Category, List<Shoe>> temp = new HashMap<>();

        try (
                Connection con = DriverManager.getConnection(connection2DB, username, password);
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery(
                        "select categoryid, shoeid  from categorypairing order by categoryid")

        ){
            boolean cont=true;
            int forCid=0;
            int sid = 0;
            int cid = 0;

            for(Category c : clist){
                List<Shoe> sTemp = new ArrayList<>();
                forCid++;

            while (rs.next()){
                if(cont){
                    sid = rs.getInt("shoeid");
                    cid = rs.getInt("categoryid");
                    if(cid>forCid){
                        rs.previous();
                        cont=false;
                        break;
                    }
                }
                    for(Shoe s : slist){
//sätter in om id matchar från categorypairing
                        if (cid==c.getId()&&sid==s.getId())
                            sTemp.add(s);
                    }
                    cont=true;
                }
//i mappen "temp"
                temp.put(c, sTemp);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return temp;
    }

    public void callAdd2Cart(int userid, int orderid, int shoeid) {

        List<Category> temp = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(connection2DB, username, password);
                CallableStatement stmnt = con.prepareCall("call addToCart(?,?,?)")
        ){
            stmnt.setInt(1, userid);
            stmnt.setInt(2, orderid);
            stmnt.setInt(3, shoeid);
            stmnt.execute();
            System.out.println("Gick igenom...");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}