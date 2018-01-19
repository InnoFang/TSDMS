# TSDMS

Textbook Subscription and Distribution Manager System.

![](https://img.shields.io/badge/IDE-Intellij%20IDEA-D5DBDB.svg)
![](https://img.shields.io/badge/Database-MySQL-82E0AA.svg)
![](https://img.shields.io/badge/language-Java-F4D03F.svg)
![](https://img.shields.io/badge/GUI-JavaFx-F1C40F.svg)

# How to import

If you want to import this project, you should modify some cofigurarion information first.

**1. Import SQL File**

Enter your MySQL, then create a database name `tsdms`

```mysql
mysql> CREATE DATABASE tsdms;
```

Then use it
```mysql
mysql> USE tsdms;
```

Now you can input the below code to import `tsdms.sql` file, and the `<project path>` is the current path to this project.
```mysql
mysql> source <project path>/tsdms.sql;
```

**2. Change Configure File**

Under the `<project path>/src/main/java` floder, there is a file called `mysql.ini`, which contains the configuration information of your MySQL , including the account and password, you should change it to your own.

Then open the `JDBCUtils.java` under the `<project path>/src/main/java/utils`, set your `mysql.ini` location path to param `INIT_FILE`

For example, my code is as follows:

```java
private final static String INIT_FILE = "F:/IDEA Project/TSDMS/src/main/java/mysql.ini";
```

you should change it to your own


**3. Import this project**

After the above steps have been completed, you can use IntelliJ IDEA to import the project.



# Screenshot

## Common

 + Login
 + Register

<br />
<br />
<div align="center" >
	<img src="./screenshot/login.jpg" />
	<img src="./screenshot/register.jpg" />
</div>
<br />
<br />

## Administrator

 + Textbook information manager
 + Add textbook
 + Add press information
 + Subscription information search
 + Distribution information search
 + Distribute textbook
 + Administrator information manager

<br />
<br />
<div align="center">
	<img src="./screenshot/admin_textbook.jpg" />
	<img src="./screenshot/addtextbook.jpg"  />
	<img src="./screenshot/press.jpg"  />
	<img src="./screenshot/subscriptionInfoSearch.jpg"  />
	<img src="./screenshot/distributionInfoSearch.jpg"  />
	<img src="./screenshot/distributeTextbook.jpg"  />
	<img src="./screenshot/confirmToDistribute.jpg"  />
	<img src="./screenshot/AdminInfo.jpg" />
</div> 

<br />
<br />

## Teacher

 + Textbook infomation manager
 + Subscribe textbook
 + Class information manager
 + Teacher information manager

<br />
<br />
<div align="center">
	<img src="./screenshot/teaTextbookSearch.jpg" />
	<img src="./screenshot/textbookSubscription.jpg" />
	<img src="./screenshot/classInfoManager.jpg"  />
	<img src="./screenshot/teacherInfo.jpg"  />
</div> 

<br />
<br />
<br />

# [License](https://github.com/InnoFang/TSDMS/blob/master/LICENSE)



			Copyright 2017 InnoFang

			Licensed under the Apache License, Version 2.0 (the "License");
			you may not use this file except in compliance with the License.
			You may obtain a copy of the License at

			   http://www.apache.org/licenses/LICENSE-2.0

			Unless required by applicable law or agreed to in writing, software
			distributed under the License is distributed on an "AS IS" BASIS,
			WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
			See the License for the specific language governing permissions and
			limitations under the License.