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

<h2> How it work </h2>
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

<!--Check Balance-->
<h3> Check Balance </h3>

<!--Deposit-->
<h3> Deposit </h3>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_11.png"/>
<!--Withdraw-->
<h3> Withdraw </h3>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_15.png"/>
  
<!--Past Transaction-->
<h3> Check Past Transaction </h3>
<img src="https://github.com/gnikkoch96/Banking-Application/blob/main/assets/Reference%20Image/Screenshot_17.png"/>

<!--Logout-->
<h3> Logout </h3>
<p> Logging out just takes the user back to the login menu again </p>

<h2> What I Learned </h2>
<!-- 1. Swing Framework to Create the GUI -->
<p> The Swing framework was used to create the GUI aspect of the program. <i> JFrame </i> is a container that can hold JComponents including other containers such as <i> JPanel. </i> 
<!-- 2. SQLite and how Database works exactly -->

<!-- 3. How to connect both of them --> 

<h2> What I Think Could be Improved On </h2>
