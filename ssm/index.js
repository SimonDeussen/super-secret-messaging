let express = require('express');
const sqLite = require('sqlite3').verbose();
let database = new sqLite.Database('./database.db', (err) => {
  if (err) {
    return console.error(err.message);
  }
  console.log('Connected to the in-memory SQlite database.');

});

database.run("CREATE TABLE IF NOT EXISTS notes (key TEXT PRIMARY KEY, value TEXT)");

const app = express();
const port = 3000;

const seperator = "."

let http = require('http').Server(app);
let io = require('socket.io')(http);

//making the public folder public
app.use("/public", express.static(__dirname + "/public"));
app.use("/resources/public", express.static(__dirname + "/resources/public"));

//serving html
app.get('/', function(req, res)
{
  res.sendFile(__dirname + '/saveMessage.html');
});

app.get(/[//].{12}[/.].{52}$/, function(req, res)
{
  res.sendFile(__dirname + '/getMessage.html');
});

app.get(/.*/, function(req, res)
{
  res.sendFile(__dirname + '/notFound.html');
});


http.listen(port, function()
{
  console.log(`server is listening on ${port}`);
  console.log(`localhost:${port}`);
});

//checking incoming connections
io.on('connection', function(socket)
{
  console.log('a user connected');

  socket.on('disconnect', function()
  {
    console.log('a user disconnected');

  });

  socket.on("writeIntoDb", function(msg)
  {
    let key = msg.split(seperator)[0];
    let value = msg.split(seperator)[1].substring(0,1000);
    console.log("db-key: " + key);
    console.log("msg: " + value);
    writeIntoDb(key, value);
  });

  socket.on("containsMessage", function(msg) {
    console.log("contains " + msg)
    let key = msg;
    readDatafromDb(key).then((result) => {
      if (result != null) {
        socket.emit("isInDatabase", "true");
        console.log("found it")
      } else {
        socket.emit("isInDatabase", "false");
        console.log("noooo couldn found it")
      }
    });
  });

  socket.on("requestData", function(msg) {
    let key = msg;
    readDatafromDb(key).then((result) => {
      console.log("read from db " +  result.value);
      socket.emit("getData", result.value);
      deleteMessageInDb(key);
    });
  });

  socket.on("hello", function(msg)
  {
    console.log("hello from " + msg);
  })

  socket.emit("test", "hello from node")
});

function writeIntoDb(key, value) {
  database.run("INSERT INTO notes (key, value) VALUES (?,?)", [key, value], (err) => {
    if (err) {
      console.log(err.message);
    }
  });
}

function readDatafromDb(key) {
  return new Promise((resolve, reject) => {
    database.get("SELECT * FROM notes WHERE notes.key = ?", [key], (err, row) => {
      if (err) {
        reject(err);
      } else {
        resolve(row);
      }
    });
  });
}

function deleteMessageInDb(key) {
  database.run("DELETE FROM notes WHERE notes.key = ?", [key], (err) => {
    if (err) {
      console.log(err);
    }
  });
}
