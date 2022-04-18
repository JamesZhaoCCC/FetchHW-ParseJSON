# FetchHW-ParseJSON

How to Run:

The JSON is being parsed from a URL so make sure you have a stable internet connection.
Make sure to have the latest version of Android Studio installed.

Run "git clone https://github.com/JamesZhaoCCC/FetchHW-ParseJSON.git" in your desired directory.
You should receive the project files and the readme.md files.

Run android studio and open the project files.

To test on an emulator, set up a virtual machine (device definition Phone resolution 1080x1920) and use the latest x86-64 system image.
To test on personal device, connect your device to the computer running Android Studio via USB cable.

Make sure to enable dev tools debugging on the device. 
(To enable dev tools, navigate to the ABout Phone section and click buiild number until it says you are a developer.
Then, navigate to system, expand the advanced tab and you should see Developer options. In Devloper options make sure you enable USB debugging.
That's it. Your device will now be able to emulate the applications in Android Studio)

Once the application runs on the device, click the PARSE button to retrieve the data grouped by listId and ordered by the id.

Summary:

Mobile application to parse JSON file through a URL using Volley to make a request
and display the list of items in a multi-column listview grouped by listId and sorted by name.
Filters out results where the name field is left blank and where the field says null.
Program is written entirely in Java and uses the Volley library and internet connection.
The data is parsed and displayed directly in the code and does not make any calls to a database.

Assignment Details:
https://fetch-hiring.s3.amazonaws.com/mobile.html

JSON Data URL:
https://fetch-hiring.s3.amazonaws.com/hiring.json
