let express = require('express');

const app = express();
const port = 3000;

let http = require('http').Server(app);
let io = require('socket.io')(http);

//making the public folder public
app.use("/public", express.static(__dirname + "/public"));

//serving html
app.get('/', function(req, res)
{
  res.sendFile(__dirname + '/index.html');
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
    let key = msg.key;
    let value = msg.value;

    writeIntoDb(key, value);
  });

  socket.on("requestData", function(msg)
  {
    let key = msg.key;
    let value = readDatafromDb(key);

    socket.emit("getData", value);
  });
});

function writeIntoDb(key, value)
{
  // ...
}

function readDatafromDb(key)
{
  //  ...
}
