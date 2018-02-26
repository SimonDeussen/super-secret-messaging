# Super Secret Messaging

This is a university project (Hochschule der Medien Stuttgart) for Functional Programming. It is  a webservice to send send secret and self destructing notes. You can create a note consisting of max. 1000 characters, and save it on our server. But wait! Before saving it on the server it will be encrypted in your browser, so the server cant read it. You will get a Link, the link contains the database key as well as the decryption key. When opening the link you will get prompted if you really want to open the notes, if so the note will get deleted in our database and decrypted again in your browser. Our ClojureScript is in ssm/src/cljs/ssm/core.cljs. We used lein cljs build to compile our project. 

## How to run it:
+ Install [node.js](https://nodejs.org/en/download/)
  + With node js comes npm, the node packet manager
+ Navigate so the ssm folder and run
  + npm install --save express
  + npm install --save sqlite3
  + npm install --save socket.io
+ Start the server
  + node index.js
+ Open your browser and to to [localhost:3000](http://localhost:3000)
