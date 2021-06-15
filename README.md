<!--Banner Image-->
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/banner.png"/>
<p> The reason I made this project is to create a simple software that utilizes a database. In this case, I used SQLite because I heard it is good for storing small amounts of data which is perfect for a sample project like this one. I also wanted to refresh my Database knowledge.</p>

<b> Description: </b> This is just a simple program to replicate the basic functionalities of a banking application. The user can login, register, check their balance, deposit theoretical money, withdraw theoretical money, and check their past transactions. Withdrawing and Depositing will be recorded in a transaction table from the local databased which is stored within the project itself and properly referened within the Datasource class using the System.getProperty("user.dir"). 

<h2> Database Design </h2> <!-- Show the Image --> 
<p> Users has a one to many relationship with transactions while transactions has only a one to one relationship with users. This means that users can have one to multiple transactions while transactions are uniquely recorded per unique individuals. </p>

<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Banking%20App%20Database%20Design.png/"/>

<h2> Technologies Used </h2>
1. Eclipse 
2. SQLite
3. SQLite Browser (GUI) 

<h2> How it works </h2>
<!--Login-->
<h3> Login </h3>
<p> When launching this program, you will be prompted with a login screen. When you try to login, there will be a validation to see whether or not your username/password combination is in the current database. If you haven't tried making an account yet (i.e. through the register), then that would be the next step to move towards the main application.</p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_1.png"/>

<!--Register-->
<h3> Register</h3>
<p> Pressing the register button, will lead you to the GUI below. The user will have to fill out all of the fields properly as I've placed a couple of validations such as email syntax validations, password and retype password validations, and whether or not the email already exists in the database or not. There is no password policy, so the user can create an easily decryptable password. Once all fields are inputted and validated, the user may press the register button to be greeted with a success dialog</p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_2.png"/>

<!--Bank App-->
<h3> Bank App (Main) </h3>
<p> This is the "main" screen to access all of the functionalities. You may choose only one activity at a time. </p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_7.png"/>
<!--Check Balance-->
<h3> Check Balance </h3>
<p> Pressing the Check Balance will access the current user's balance which was queried from the database </p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/check%20balance.png"/>

<!--Deposit-->
<h3> Deposit </h3>
<p> The user can deposit theoretical money through the desktop-app, and it will be recorded into the database (more specifically in the transactions table) </p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_11.png"/>
  
<!--Withdraw-->
<h3> Withdraw </h3>
<p> The user can withdraw theoretical money, and it will also be recorded into the local database </p>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_15.png"/>
  
<!--Past Transaction-->
<h3> Check Past Transaction </h3>
<p> Each transaction (i.e. Deposit or Withdraw) will be recorded along with their date and time. The user is able to check all of the transactions they've ever done on the desktop-application </p> 
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_17.png"/>

<!--Logout-->
<h3> Logout </h3>
<p> Logging out just takes the user back to the login menu again </p>

<h2> What I Learned </h2>
<!-- 1. Swing Framework to Create the GUI -->
<h3> Swings Framework (GUI) </h3>
<p> The Swing framework was used to create the GUI aspect of the program. <i> JFrame </i> is a container that can hold JComponents including other containers such as <i> JPanel. </i> </p>
<p> There is a way to organize the components within the containers by setting a layout for each container. By default <i> JFrame </i> has BorderLayout and <i> JPanel </i> has FlowLayout. For my project I utilized GridLayout a lot as it was much simpler to use to organize the way I wanted my design to be. </p>
<p> I also learned that the setSize() is to be used only when there is no parent layout manager which is the case for all of the components within the JFrame. Instead for the nested containers, I would be using the setPreferredSize(). </p>
<p> WindowListeners were used here to activate and reactivate panels that were disabled. I used a third-party library to disable panels called DisabledPanels (which can be read in more detail here: https://tips4java.wordpress.com/2009/08/02/disabled-panel/). Essentially it allows me to disable all of the compnents from a JPanel which I needed because I wanted the user to be able to access only one JFrame at a time. </p>
  
<!-- 2. SQLite and how Database works exactly -->
<h3> SQLite (Database) </h3>
<p> For this project I had to review my Database knowledge again as its been awhile.</p>
<p> The most import queries that I did for my project would be the SELECT and INSERT. SELECT would be used to query the past transactions from the table, while the INSERT command was used to insert deposit and withdraw transactions. </p>

<!-- 3. How to connect both of them --> 

<h2> What I Think Could be Improved On </h2>
