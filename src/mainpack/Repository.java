package mainpack;

import tables.*;

import java.util.*;

public class Repository {

    private List<Costumer> allCostumers = new ArrayList<>();
    private List<PurchaseOrder> allOrders = new ArrayList<>();
    private List<Shoe> allShoes = new ArrayList<>();
    private List<Category> allCategories = new ArrayList<>();
    private Map<Category, List<Shoe>> pairCatNshoe = new HashMap<>();
    private TableMaker tm = new TableMaker();


    Repository() {
        setupLists();
    }

    private void setupLists() {

        allCategories=tm.makeCategoryList();
        allShoes=tm.makeShoeList();
        allOrders=tm.makeOrderList();
        allCostumers=tm.makeCostumerList();
        pairCatNshoe=tm.makePairMap(allCategories, allShoes);
    }

    private int getTotalOrderValue(Costumer c) {

        List<Purchase> purchases = c.getCostumerPurchases();
        int temp =0;
        for(Purchase p : purchases){
            int id = p.getId();
            for(PurchaseOrder po: allOrders){
                if(id == po.getPurchaseid()){
                    List<Sale> sales = po.getallSalesInOrder();
                    for(Sale s : sales){
                        id = s.getShoeid();
                        for(Shoe shoe : allShoes){
                            if(id == shoe.getId())
                                temp += shoe.getPrice();
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Costumer idInCostumerList(String input) {



            for(Costumer c : allCostumers)
                if(input.equalsIgnoreCase(c.getFirstname()))
                    return c;


        return null;
    }

    public void printStage1() {

//        for (Costumer c : allCostumers){
//        System.out.println(c.toString()+"\t"+getTotalOrderValue(c)+" totalt spenderat");
//        }
        allCostumers.forEach(costumer -> System.out.println(costumer.toString()+"\t"+getTotalOrderValue(costumer)+" totalt spenderat"));
    }

    public String printStage2() {

        String txt = "";
        for (Map.Entry<Category, List<Shoe>> cs :pairCatNshoe.entrySet()){
            txt+="\n"+cs.getKey().toString()+"\n ";
            for (Shoe s : cs.getValue())
                txt+= s.toString()+"\n";
        }
        return txt;
    }



    private List<PurchaseOrder> getOrdersForCostumer(List<Purchase> costumerPurchases) {

        List<PurchaseOrder> temp = new ArrayList<>();
        for(Purchase p : costumerPurchases) {
            int pid = p.getId();
            for (PurchaseOrder po : allOrders)
                if(pid==po.getPurchaseid()&&!po.isDispatched())
                    temp.add(po);
        }
            return temp;
    }

    public void printStage3(Costumer user) {

        List<PurchaseOrder> temp;
        temp=getOrdersForCostumer(user.getCostumerPurchases());
        int i = 0;
        for (PurchaseOrder po : temp){
            i++;
            System.out.println(i+". :" + po.getPurchaseDate());
        }
        System.out.println("För ny skriv något ny");
    }

    public void addToCart(String input, Costumer user) {
        Scanner sc = new Scanner(System.in);
        int id=0;
        PurchaseOrder purchaseOrder;
        Shoe shoeOrder = null;
        boolean notExist=true;
        List<PurchaseOrder> temp = getOrdersForCostumer(user.getCostumerPurchases());
        try {
            id = Integer.parseInt(input)-1;
        }
        catch (java.lang.NumberFormatException e){input= null; }

        if(input!=null&&!(temp.size()<id-1)){
            purchaseOrder = temp.get(id);
        }
        else
            purchaseOrder= new PurchaseOrder(0,0,"0");

        while(true) {
            System.out.println("färg");
            String färg = sc.nextLine();
            System.out.println("märke");
            String märke = sc.nextLine();

            for (Shoe s : allShoes) {
                if (färg.equalsIgnoreCase(s.getColor()) && märke.equalsIgnoreCase(s.getBrand())){
                    shoeOrder = s;
                    if(s.getStockAmount()<1){
                        System.out.println("den slut");
                        notExist=false;
                        break;
                    }
                        else{
                    System.out.println("du valde"+ s.toString());
                    notExist=false;
                    break;
                    }
                }
            }
            if(notExist)
                System.out.println("fanns inte");

            assert shoeOrder != null;
            tm.callAdd2Cart(user.getId(), purchaseOrder.getId(), shoeOrder.getId());
        shoeOrder.setStockAmount(shoeOrder.getStockAmount()-1);
            System.out.println("vara beställd"+shoeOrder.toString());
            System.out.println("Skriv nej om ej fortsätt");
            if(sc.nextLine().equalsIgnoreCase("nej"))
                break;
        }
    }

}
