package com.example.layout.contextmenu;
//Package objects contain version information about the implementation and specification of a Java package
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //here i have created main class
//public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a parent class
    // ListView is a view which groups several items and display them in vertical scrollable list. The list items are automatically
    // inserted to the list using an Adapter that pulls content from a source such as an array or database.
//The Adapter provides access to the data items. The Adapter is also responsible for making a View for each item in the data set.
    ListView listView;
    Adapter adapter;
    int index=0;
    //starting array index is 0
    //Taking string of name and number
    private final static String name[]={"Bhawani","Neha","Ritika","Ganesh","Chandan","Vikash","Vishwas","Mithun","Barun","Sudha","Rajesh"};
    private final static String number[]={"6754389765","8975432189","8954762376","9865349654","9845063478","89855667432","9812546754",
            "9097534246","7589435686","8165890432","7954637892"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.
// This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
// We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has
// completed.The savedInstanceState Bundle is same as the one used in onCreate().

        super.onCreate(savedInstanceState);
        // call the super class onCreate to complete the creation of activity like the view hierarchy
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design
        listView=(ListView)findViewById(R.id.listView);
       // View IDs need not be unique throughout the tree, but it is good practice to ensure that they are at least unique within the
        //part of the tree you are searching of list view
        adapter = new Adapter(this,name,number);
//creating adapter object for adding name and number
        //Sets the data behind this ListView.
        listView.setAdapter(adapter);
        //Interface definition for a callback to be invoked when an item in this AdapterView has been clicked.
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);
        //Registers a context menu to be shown for the given view (multiple views can show the context menu).
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        //Callback method to be invoked when an item in this AdapterView has been clicked
        //A toast is a view containing a quick little message for the user. The toast class helps you create and show those.
        //Make a standard toast that just contains a text view with the text from a resource.
        //Context: The context to use. Usually your Application or Activity object.
        //text:The text to show. Can be formatted text.
        //int: How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
        //LENGTH_SHORT :Show the view or text notification for a short period of time
        //show():Show the view for the specified duration.
        Toast.makeText(this, "Long press to Call /SMS", Toast.LENGTH_SHORT).show();
        index=position;
        //here index of the array is equal to position
    }

    @Override
    // creating context Menu
    //Android context menu appears when user press long click on the element. It is also known as floating menu.
    //Called when the context menu for this view is being built.
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // adding header in the menu
        menu.setHeaderTitle("Select the Action");
        // adding call and send sms action in the menu
        menu.add(0, 1, 0, "Call");
        menu.add(0, 2, 1, "Send SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //This hook is called whenever an item in a context menu is selected.
        //Applying conditions for cal;l and sms
        try {
            //  call
            if(item.getItemId()==1 && item.getGroupId()==0){
                //here we are creating intent
                Intent i=new Intent();
                //intent is an abstract description of an operation to be performed
                //setting an action to dial the number and here the numbers are in the form of inderx that which are given in the array
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+number[index]));
                //here the activity get started for call
                startActivity(i);
            }
            // sms
            else if(item.getItemId()==2 && item.getGroupId()==0){
                //Creating  the intent to show number
                Intent i=new Intent();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+ number[index])));
                //Starting the intent for msg
                startActivity(i);
            }
            else{
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }


    }
//Initialize the contents of the Activity's standard options menu. You should place your menu items in to menu.
    //Returns a MenuInflater with this context and returns the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}