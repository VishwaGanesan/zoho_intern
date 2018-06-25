import java.util.*;
class Customers
{
    private static Integer CID = 1001; 
    
    private String name;
    private Integer mobile;
    private Integer customerId;
    ArrayList <Invoices> invoiceList = new ArrayList<Invoices>();
    private static HashMap <Integer,Customers> customersList = new HashMap <Integer,Customers>();
    
    Customers()
    {
        this.customerId= ++CID;
    }
    public void setName(String s)
    {
        this.name = s;
    }
    public void setMobile(Integer n)
    {
        this.mobile = n;
    }
     public void setInvoices(Invoices i)
    {
        this.invoiceList.add(i);
    }
    public String  getName()
    {
        return this.name;
    }
    public Integer getMobile()
    {
        return this.mobile;
    }
    public ArrayList getInvoices()
    {
        return this.invoiceList;
    }
    public static Customers createCustomers()
    {
        Customers c = new Customers();
        c.setName("Vishwa");
        c.setMobile(8531);
        customersList.put(c.customerId,c);
        return c;
    }
    public static ArrayList retriveCustomerInvoices(Integer id)
    {
        Customers c = customersList.get(id);
        return c.invoiceList;
    }
  }
class Invoices
{
    private static Integer ID = 0;
    
    private static ArrayList <Items> itemsList = new ArrayList<Items>();
    //private HashMap <Integer,Integer> quantity = new HashMap<Integer,Integer>(); 
    private Integer total = 0;
    private boolean isDiscount;
    private Double discount;
    private Integer invoiceId;
    private Customers customerDetails;
    Invoices()
    {
        this.invoiceId = ++ID;
    }
    public void setCustomerDetails(Customers c)
    {
        this.customerDetails = c;
    }
    public void setItems(Integer i,Integer Q)
    {
        Items a = Items.retriveItems(i);
        this.itemsList.add(a);
        this.total+=(a.getItemPrice()*Q);
    }
    public Customers getCustomerDetails()
    {
        return this.customerDetails;
    }
    public ArrayList getItemsList()
    {
        return this.itemsList;
    }
    public Integer getTotal()
    {
        return this.total;
    }
    public Integer getInvoiceId()
    {
        return this.invoiceId;
    }
    public static  Invoices createInvoice(Customers c)
    {
         Invoices in = new Invoices();
         in.setCustomerDetails(c);
         in.setItems(2,2);
         in.setItems(1,1);
         c.invoiceList.add(in);
         return in;
    }
}
class Items
{
    private Integer itemId;
    private Integer itemPrice;
    private String itemName;
    private static HashMap <Integer,Items> inventory = new HashMap <Integer,Items>();
    Items(Integer id,Integer price,String name)
    {
        this.itemId = id;
        this.itemPrice = price;
        this.itemName  = name;
        inventory.put(this.itemId,this);
    }
    public void setItemId(Integer id)
    {
        this.itemId = id;
    }
    public void setItemPrice(Integer p)
    {
        this.itemPrice = p;
    }
    public void setItemName(String s)
    {
        this.itemName = s;
    }
    public Integer getItemId()
    {
        return this.itemId;
    }
    public Integer getItemPrice()
    {
        return this.itemPrice;
    }
    public String getItemName()
    {
        return this.itemName;
    }
    public static Items retriveItems(Integer id)
    {
        return inventory.get(id);
    }
    public static void createItems()
    {
         Items a = new Items(1,40,"Dosa");
         Items b = new Items(2,50,"Idly");
         Items c = new Items(3,100,"Vada");
    }
}
class Root
{
    public static void main(String args[])
    {
        ArrayList <Invoices> test = new ArrayList <Invoices>();
        int total = 0;
        Items.createItems();
        Customers c = Customers.createCustomers();
        Invoices in1  = Invoices.createInvoice(c);
        Invoices in2 = Invoices.createInvoice(c);
        System.out.println(in1.getTotal());
        test = Customers.retriveCustomerInvoices(1002);
        for(Invoices a: test)
        {
            System.out.println(a.getInvoiceId());
            Customers ct=a.getCustomerDetails();
            System.out.print(ct.getName()+" "+ct.getMobile()+" ");
            for(Items i : a.getItemsList())
            {
                System.out.println(i.getItemId()+" "+i.getItemName()+" "+i.getItemPrice()+" "+i.getTotal());
                total+=i.getTotal();
            }
        }
        System.out.println(total);
    }
}
