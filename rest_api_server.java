import java.util.*;
class PageNotFound extends Exception
{
    String s;
    PageNotFound(String str)
    {
        s=str;
    }
    public String toString()
    {
        return ("code:"+s+",message:Page not found");
    }
}
class MethodNotImplemented extends Exception
{
    String s;
     MethodNotImplemented(String str)
    {
        s=str;
    }
    public String toString()
    {
        return ("code:"+s+",message:Method not Implemented");
    }
}
class Response
{
    public static void get(String cust_id)
    {
        String response= Customer.retrive_customer(cust_id);
        System.out.println(response);
    }
    public static void post(String f_name,String l_name,String email,String mobile)
    {
      String  response=Customer.add_customer(f_name,l_name,email,mobile);
       System.out.println(response);
    }
}
class Request
{
    private String[] url= new String[100];
    private String f_name,l_name,email,mobile,cust_id;
    int length,get_length = 3;
    String s[]={"firstname","lastname","email","mobile"};
    Request(String s)
    {
        url=s.split("\\s|\\-|\\/|\\=|\\?|\\&");
        length = url.length;
        parser();
    }
    void parser()
    {
        try{
             if(url[1].equals("customers"))
                {
                    if(url[0].equals("post") && length <=10)
                    {
                            if(url[2].equals("first_name"))
                            {
                                f_name = url[3];
                            }
                            else
                            {
                                throw new PageNotFound("404");
                            }
                            if(url[4].equals("last_name"))
                            {
                                l_name = url[5];
                            }
                            else
                            {
                                throw new PageNotFound("404");
                            }
                            if(url[6].equals("email"))
                            {
                                email = url[7];
                            }
                            else
                            {
                               throw new PageNotFound("404");
                            }
                            if(url[8].equals("mobile"))
                            {
                                mobile = url[9];
                            }
                            else
                            {
                                throw new PageNotFound("404");
                            }
                        Response.post(f_name,l_name,email,mobile);               // call a post;
                    }
                    else if(url[0].equals("get"))
                    {
                        if(length==get_length)
                        {
                            cust_id = url[2];
                            Response.get(cust_id);                                              //call a get
                        }
                        else
                        {
                            System.out.println("Invalid URL");
                        }
                    }
                    else
                    {
                        throw new MethodNotImplemented("501");
                    }
                }
                else
                {
                      System.out.println("error");
                }
            }
         catch(PageNotFound exp)
         {
    		System.out.println(exp) ;
    		System.exit(0);
    	  }
    	  catch(MethodNotImplemented exp)
    	  {
    	      System.out.println(exp);
    	      System.exit(0);
    	  }
    }
}
class Customer
{
    static int id=1;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private int customer_id; 
    private static HashMap <Integer,Customer> cust = new HashMap<Integer,Customer>();
    private static HashMap <String,Integer>  otp = new HashMap<String,Integer>();
    public void set_first_name(String s)
    {
        this.first_name = s;
    }
      public void set_last_name(String s)
    {
        this.last_name = s;                         // setter
    }
      public void set_email(String s)
    {
        this.email = s;
    }
      public void set_mobile(String s)
    {
        this.mobile= s;
    }
    public  String get_first_name()
    {
        return first_name;
    }
    public String get_last_name()               //getter
    {
        return last_name;
    }
    public String get_email()
    {
        return email;
    }
    public String get_mobile()
    {
        return mobile;
    }
    public int  add()
    {
        this.customer_id = ++id;
        return id-1;
    }
    public static String add_customer(String a,String b,String cd,String d)
    {
        String s,message;
        int code = 201;
        message = "created";
        Customer c = new Customer();
        c.set_first_name(a);
        c.set_last_name(b);
        c.set_email(cd);                                  // add mobile_number;
        c.set_mobile(d);
        cust.put(c.id,c);
        s="status code:"+code+",message:"+message;
        return s;
    }
    public static String retrive_customer(String cust_id)
    {
        int id=Integer.parseInt(cust_id);
        String s = new String();
        Customer h  = new Customer();
        if(cust.containsKey(id))
        {
             h = cust.get(id);
             s="status code: 200,message ok{firstname:"+h.first_name+",lastname:"+h.last_name+",email:"+h.email+",mobile:"+h.mobile+"}";
        }
        else
        {
            System.out.println("Customer Not Found");
        }
        return s;
    }
}
public class root{

     public static void main(String []args){
       
        String s = "posts/customers?first_name=vishwa&last_name=ganesan&email=gvishwa1997@gmail.com&mobile=96342422324";
       String p = "get/customers/1";
        Request r = new Request(s);
        Request a = new Request(p);
        }
}
